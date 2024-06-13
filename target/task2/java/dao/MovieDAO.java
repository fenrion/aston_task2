package dao;

import db.ConnectionManager;
import db.ConnectionManagerImpl;
import entity.Actor;
import entity.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * data access object for movies table
 */
public class MovieDAO {
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
    private static MovieDAO instance;

    private MovieDAO() {
    }

    public static synchronized MovieDAO getInstance() {
        if (instance == null) {
            instance = new MovieDAO();
        }
        return instance;
    }

    /**
     * show movie list by actor id
     * @param index
     * @return
     */
    public List<Movie> showMoviesByActorId(int index) {
        List<Movie> movies = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from movies join actors_movies am on movies.id_mov = am.movies_id where actor_id=(?)")) {
            preparedStatement.setInt(1, index);
            ResultSet resultSet = preparedStatement.executeQuery();
            movies = new ArrayList<>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setName(resultSet.getString("name_mov"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    /**
     * save movieList from actor
     * @param actor
     * @param index
     */
    public void saveFromActor(Actor actor, int index) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into movies(name_mov) values (?);");
            List<Movie> movies = actor.getMovieList();
            for (Movie movie : movies) {
                preparedStatement.setString(1, movie.getName());
                if (!existsByName(movie.getName())) {
                    preparedStatement.executeUpdate();
                }
                int indexMovie = getIdMovieByName(movie);

                PreparedStatement prStatement = connection.prepareStatement("insert into actors_movies values (?,?)");
                prStatement.setInt(1, index);
                prStatement.setInt(2, indexMovie);
                prStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get id movie by movie name
     * @param movie
     * @return
     */
    private int getIdMovieByName(Movie movie) {
        int result = 0;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select id_mov from movies where name_mov=(?) limit 1;");
            preparedStatement.setString(1, movie.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("id_mov");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * check exist movie by name
     * @param name
     * @return
     */
    private boolean existsByName(String name) {
        boolean isExists = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from movies where name_mov=(?) limit 1")) {

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("id_mov") > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExists;
    }

    /**
     * delete relation actor-movie from actors_movies
     * @param actorId
     */
    public void deleteByActorId(Integer actorId) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from actors_movies where actor_id=(?)");
            preparedStatement.setInt(1, actorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
