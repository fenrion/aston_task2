package dao;

import db.ConnectionManager;
import db.ConnectionManagerImpl;
import dto.ActorDTO;
import entity.Actor;
import service.ActorService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
    private static ActorDAO instance;

    private ActorDAO() {
    }
    public static synchronized ActorDAO getInstance() {
        if (instance == null) {
            instance = new ActorDAO();
        }
        return instance;
    }


    public void saveActor(Actor actor){
        try(Connection connection = connectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Actors VALUES (?,?)");
            preparedStatement.setInt(1,actor.getId());
            preparedStatement.setString(2,actor.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Actor> showActors() {
        List<Actor> actorList;
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from actors");
            ResultSet resultSet = preparedStatement.executeQuery();
            actorList = new ArrayList<>();
            while (resultSet.next()) {
                Actor actor = new Actor();
                actor.setId(resultSet.getInt(1));
                actor.setName(resultSet.getString(2));
                actorList.add(actor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actorList;
    }
}
