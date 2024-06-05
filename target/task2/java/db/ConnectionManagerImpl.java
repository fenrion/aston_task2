package db;

import util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManagerImpl implements ConnectionManager{
    private static final String DRIVER_CLASS_KEY = "org.postgres.Driver";
    private static final String URL_KEY = "jdbc:postgresql://localhost:5432/aston_task2";
    private static final String USERNAME_KEY = "postgres";
    private static final String PASSWORD_KEY = "1m3zfklmb";
    private static ConnectionManager instance;

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManagerImpl();
            loadDriver(PropertiesUtil.getProperties(DRIVER_CLASS_KEY));
        }
        return instance;
    }

    private static void loadDriver(String driverClass) {
        try {
            Class.forName(driverClass);
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
