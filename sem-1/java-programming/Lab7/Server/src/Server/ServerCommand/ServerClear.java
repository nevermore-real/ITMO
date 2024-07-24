package Server.ServerCommand;

import MajorClasses.Printer;
import db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerClear extends ServerCommand {
    private static final String CLEAR_COOR = "DELETE FROM coordinates WHERE ";
    private static final String CLEAR_PERSON = "DELETE FROM person";
    private static final String CLEAR_PRODUCT = "DELETE FROM product CASCADE WHERE fk_user_id = ?";
    boolean flag = false;


    public void execute(DatabaseHandler databaseHandler){
        collectionManager.clear();
        try{
            PreparedStatement statement = databaseHandler.connection.prepareStatement(CLEAR_COOR);
            statement.execute();
            statement.close();
            statement = databaseHandler.connection.prepareStatement(CLEAR_PERSON);
            statement.execute();
            statement.close();
            statement = databaseHandler.connection.prepareStatement(CLEAR_PRODUCT);
            statement.setInt(1,databaseHandler.getUserId());
            statement.execute();
            statement.close();

        }catch (SQLException e){
            Printer.printSQLException();
        }
    }
}
