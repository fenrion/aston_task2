package util;

import db.ConnectionManager;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitSqlScheme {
    private static final String SCHEME = "schema.sql";
    private static final String DATA = "data.sql";
    private static String schemeSql;
    private static String dataSql;

    static {
        loadInitSQL();
    }

    private InitSqlScheme() {
    }

    public static void initSqlScheme(ConnectionManager connectionManager) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(schemeSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initSqlData(ConnectionManager connectionManager) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(dataSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadInitSQL() {
        try (InputStream inFile = InitSqlScheme.class.getClassLoader().getResourceAsStream(SCHEME)) {
            schemeSql = new String(inFile.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        try (InputStream inFile = InitSqlScheme.class.getClassLoader().getResourceAsStream(DATA)) {
            dataSql = new String(inFile.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

}
