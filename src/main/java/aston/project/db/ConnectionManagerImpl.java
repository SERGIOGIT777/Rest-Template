package aston.project.db;

import aston.project.exception.DataBaseDriverLoadException;
import aston.project.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManagerImpl implements ConnectionManager{
    private static final String DRIVER_CLASS_KEY = "db.driver-class-name";
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static ConnectionManager instance;

    private ConnectionManagerImpl() {
    }

    public static synchronized ConnectionManager getInstance(){
        if (instance == null){
            instance = new ConnectionManagerImpl();
            loadDriver(PropertiesUtil.getProperty(DRIVER_CLASS_KEY));
        }
        return instance;
    }

    private static void loadDriver(String driverClassName){
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e){
            throw new DataBaseDriverLoadException("Database driver not loaded.");
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                PropertiesUtil.getProperty(URL_KEY),
                PropertiesUtil.getProperty(USERNAME_KEY),
                PropertiesUtil.getProperty(PASSWORD_KEY)
        );
    }
}
