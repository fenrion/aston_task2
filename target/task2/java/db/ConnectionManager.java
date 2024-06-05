package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ConnectionManager {
    Connection getConnection() throws SQLException;

}
