package DataBase;

import ConfigUtility.ConfigReader;
import ConfigUtility.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    public DBConnection() throws SQLException {
        createConnection();
    }

    private void createConnection() throws SQLException {
        Configuration configuration = ConfigReader.readConfig("src/test/GeneralConfiguration.xml");
        connection = DriverManager.getConnection(getPreparedURL(configuration),configuration.getDatabaseConfig().getUserName(), configuration.getDatabaseConfig().getPassword());
    }

    public static synchronized DBConnection getInstance() throws SQLException {

        if (instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    public static String getPreparedURL(Configuration configuration) {
        return "jdbc:mysql://localhost:" + configuration.getDatabaseConfig().getPort() + "/" + configuration.getDatabaseConfig().getDataBase() +
                "?allowPublicKeyRetrieval=true&useSSL=false";
    }

    public Connection getConnection() {
        return connection;
    }
}
