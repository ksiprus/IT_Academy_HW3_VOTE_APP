package by.ksiprus.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";
    private static final String URL = "jdbc:postgresql://localhost:5433/postgres";

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to register PostgreSQL driver", e);
        }
    }

    private ConnectionManager() {
        // Приватный конструктор для предотвращения создания экземпляров
    }

    public static Connection openConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to open connection", e);
        }
    }
}

