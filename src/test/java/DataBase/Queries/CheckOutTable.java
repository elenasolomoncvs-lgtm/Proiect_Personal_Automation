package DataBase.Queries;

import ObjectData.CheckOutObject;

import java.sql.SQLException;
import java.sql.Statement;

public class CheckOutTable extends CommonTable{

    public CheckOutTable () throws SQLException{
    }

    public synchronized void insertTableRow(CheckOutObject data) throws SQLException {
        Statement statement = dbConnection.getConnection().createStatement();
        String query = "insert into Users(firstname, lastname, email, phone_number, address, city, state) " +
                "values ('" + data.getFirstName() + "'" + "," +
                "'" + data.getLastName() + "'" + "," +
                "'" + data.getEmailAddress() + "'" + "," +
                "'" + data.getPhoneNumber() + "'" + "," +
                "'" + data.getAddress() + "'" + "," +
                "'" + data.getCity() + "'" + "," +
                "'" + data.getState()+ "'" + ");";
        statement.execute(query);
    }
}
