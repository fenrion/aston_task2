package dao;

import db.ConnectionManager;
import db.ConnectionManagerImpl;
import entity.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * data access object for Actor table
 */
public class ActorDAO {
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
    private final PhoneNumberDAO phoneNumberDAO = PhoneNumberDAO.getInstance();
    private static ActorDAO instance;

    private ActorDAO() {
    }

    public static synchronized ActorDAO getInstance() {
        if (instance == null) {
            instance = new ActorDAO();
        }
        return instance;
    }

    /**
     * remove actor by id
     * @param index
     */
    public void removeActor(int index) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from actors where act_id=(?)");
            preparedStatement.setInt(1, index);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * update actor by id
     * @param actor
     * @param index
     */
    public void updateActor(Actor actor, int index) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update actors set act_name=(?) where act_id=(?)");
            preparedStatement.setString(1, actor.getName());
            preparedStatement.setInt(2, index);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * show all actors
     * @return
     */
    public List<Actor> showActors() {
        List<Actor> actorList;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from actors order by act_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            actorList = new ArrayList<>();
            while (resultSet.next()) {
                Actor actor = new Actor();
                actor.setId(resultSet.getInt(1));
                actor.setName(resultSet.getString(2));
                actor.setMovieList(null);
                actor.setPhoneNumberList(null);
                actorList.add(actor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actorList;
    }

    /**
     * show single actor by id
     * @param index
     * @return
     */
    public Actor showActor(int index) {
        Actor actor;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from actors where act_id=(?)");
            preparedStatement.setInt(1, index);
            ResultSet resultSet = preparedStatement.executeQuery();
            actor = new Actor();
            resultSet.next();
            actor.setId(resultSet.getInt("act_id"));
            actor.setName(resultSet.getString("act_name"));
            actor.setMovieList(null);
            actor.setPhoneNumberList(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actor;
    }

    /**
     * save actor
     * @param actor
     * @return
     */
    public int saveActor(Actor actor) {
        int result;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into actors(act_name) values (?);");
            preparedStatement.setString(1, actor.getName());
            preparedStatement.executeUpdate();
            result = getIdByActorName(actor);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * get id from table by actor's name
     * @param actor
     * @return
     */
    private int getIdByActorName(Actor actor) {
        int result = 0;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select act_id from actors where act_name=(?) limit 1;");
            preparedStatement.setString(1, actor.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("act_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
