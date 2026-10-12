package DataBase.Queries;

import DataBase.DBConnection;

import java.sql.SQLException;

public class CommonTable {
    public DBConnection dbConnection;

    public CommonTable() throws SQLException {
        dbConnection = DBConnection.getInstance();
    }
}
