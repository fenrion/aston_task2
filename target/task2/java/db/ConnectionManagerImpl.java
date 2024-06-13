package db;

import util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class for connection db
 */
public final class ConnectionManagerImpl implements ConnectionManager{
    private static final String DRIVER_CLASS_KEY = "db.driver";
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "user";
    private static final String PASSWORD_KEY = "password";

//    private static final String DRIVER_CLASS_KEY = "org.postgresql.Driver";
//    private static final String URL_KEY = "jdbc:postgresql://localhost:5432/aston_task2";
//    private static final String USERNAME_KEY = "postgres";
//    private static final String PASSWORD_KEY = "1m3zfklmb";
    private static ConnectionManager instance;

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManagerImpl();
            loadDriver(DRIVER_CLASS_KEY);
        }
        return instance;
    }

    private static void loadDriver(String driverClass) {
        try {
            Class.forName(PropertiesUtil.getProperties(driverClass));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not loaded.");
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                PropertiesUtil.getProperties(URL_KEY),
                PropertiesUtil.getProperties(USERNAME_KEY),
                PropertiesUtil.getProperties(PASSWORD_KEY)
        );
    }
}
