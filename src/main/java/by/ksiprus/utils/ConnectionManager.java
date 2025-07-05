package by.ksiprus.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public final class ConnectionManager {
    private static final ComboPooledDataSource dataSource;

    static {
        try {
            dataSource = new ComboPooledDataSource();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка инициализации пула  соединений!!!", e);
        }
    }

    private ConnectionManager() {
    }

    public static Connection openConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closePool() {
        if (dataSource == null) {
            dataSource.close();
        }
    }
}

