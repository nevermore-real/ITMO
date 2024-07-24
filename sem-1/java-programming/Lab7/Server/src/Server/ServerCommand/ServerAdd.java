package Server.ServerCommand;

import MajorClasses.Coordinates;
import MajorClasses.Person;
import MajorClasses.Printer;
import db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServerAdd extends ServerCommand {
    private static final String ADD_PRODUCT= "INSERT INTO product VALUES(nextval('prod_seq'),?,?,?,?,?,?)";
    private static final String ADD_PERSON = "INSERT INTO person VALUES(nextval('pers_seq'),currval('prod_seq'),?,?,?,?)";
    private static final String ADD_COOR = "INSERT INTO coordinates VALUES(nextval('coor_seq'),currval('prod_seq'),?,?)";
    public void execute(DatabaseHandler databaseHandler){
        Printer.printAdd(collectionManager.add(product));
        try {
            PreparedStatement statement = databaseHandler.connection.prepareStatement(ADD_PRODUCT);
            statement.setInt(1,databaseHandler.getUserId());
            statement.setString(2, product.getName());
            statement.setString(3,product.getCreationDate().toString());
            statement.setFloat(4,product.getPrice().floatValue());
            statement.setString(5, product.getPartNumber());
            statement.setString(6, product.getUnitOfMeasure().toString());
            statement.execute();
            statement.close();
            // new statement
            statement = databaseHandler.connection.prepareStatement(ADD_PERSON);
            Person person = product.getPerson();
            statement.setString(1, person.getName());
            statement.setFloat(2,person.getHeight());
            statement.setFloat(3,person.getWeight());
            statement.setString(4, person.getEyeColor().toString());
            statement.execute();
            statement.close();
            // new statement
            Coordinates coor = product.getCoordinates();
            statement = databaseHandler.connection.prepareStatement(ADD_COOR);
            statement.setFloat(1, coor.getX().floatValue());
            statement.setFloat(2,coor.getY());
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Printer.printSQLException();
        }
    }
}
