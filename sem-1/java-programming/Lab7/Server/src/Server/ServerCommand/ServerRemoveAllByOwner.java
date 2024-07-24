package Server.ServerCommand;


import MajorClasses.Printer;
import db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerRemoveAllByOwner extends ServerCommand {
    
    public void execute(DatabaseHandler databaseHandler){
        collectionManager.removeByOwner(arrayArgs.get(0));
        Printer.printRemoveAll(true);
        String owner = arrayArgs.get(0);
        final String GET_PRODUCTS_ID = "SELECT fk_product_id FROM person WHERE name = '" + owner +"';";
        String REMOVE_PRODUCT;
        String REMOVE_COOR;
        String REMOVE_PERSON;
        try {
            PreparedStatement statement = databaseHandler.connection.prepareStatement(GET_PRODUCTS_ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("fk_product_id");
                REMOVE_PRODUCT = "DELETE FROM product WHERE product_id = " + id;
                REMOVE_COOR = "DELETE FROM coordinates WHERE fk_product_id = " + id;
                REMOVE_PERSON = "DELETE FROM person WHERE fk_product_id = " + id;
                PreparedStatement statement111 = databaseHandler.connection.prepareStatement(REMOVE_PERSON);
                statement111.execute();
                statement111.close();
                PreparedStatement statement222 = databaseHandler.connection.prepareStatement(REMOVE_COOR);
                statement222.execute();
                statement222.close();
                PreparedStatement statement333 = databaseHandler.connection.prepareStatement(REMOVE_PRODUCT);
                statement333.execute();
                statement333.close();
                System.out.println("Продукт удален");
            }
            statement.close();
        }catch (SQLException e){
            Printer.printSQLException();
        }
    }
}
