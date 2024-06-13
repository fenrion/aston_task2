package dao;

import db.ConnectionManager;
import db.ConnectionManagerImpl;
import entity.Actor;
import entity.PhoneNumber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumberDAO {
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
    private static PhoneNumberDAO instance;

    private PhoneNumberDAO() {
    }

    public static synchronized PhoneNumberDAO getInstance() {
        if (instance == null) {
            instance = new PhoneNumberDAO();
        }
        return instance;
    }

    /**
     * show phone numbers by actor id
     * @param index
     * @return
     */
    public List<PhoneNumber> showNumbersByActorId(int index) {
        List<PhoneNumber> phoneNumberList = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from phone_numbers where actor_id=?")) {
            preparedStatement.setInt(1, index);
            ResultSet resultSet = preparedStatement.executeQuery();
            phoneNumberList = new ArrayList<>();
            while (resultSet.next()) {
                PhoneNumber phoneNumber = new PhoneNumber();
                phoneNumber.setNumber(resultSet.getString("ph_number"));
                phoneNumberList.add(phoneNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return phoneNumberList;
    }

    /**
     * delete phone number by actor id
     * @param actorId
     */
    public void deleteByActorId(Integer actorId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM phone_numbers WHERE actor_id = (?)")) {

            preparedStatement.setInt(1, actorId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * save phone numbers from actor
     * @param actor
     * @param index
     */
    public void saveFromActor(Actor actor, int index) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into phone_numbers(actor_id,ph_number) values (?,?);");
            List<PhoneNumber> phoneNumbers = actor.getPhoneNumberList();
            for (PhoneNumber phoneNumber : phoneNumbers) {
                preparedStatement.setInt(1, index);
                preparedStatement.setString(2, phoneNumber.getNumber());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * update phone numbers list by actor id
     * @param phoneNumbers
     * @param index
     */
    public void updateNumber(List<PhoneNumber> phoneNumbers, int index) {
        try (Connection connection = connectionManager.getConnection()) {
            deleteById(index);
            for (PhoneNumber phoneNumber : phoneNumbers) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into phone_numbers(actor_id,ph_number) values (?,?);");
                preparedStatement.setInt(1,index);
                preparedStatement.setString(2,phoneNumber.getNumber());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * delete by actor id
     * @param index
     */
    public void deleteById(int index) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from phone_numbers where actor_id=(?)")) {
            preparedStatement.setInt(1, index);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
