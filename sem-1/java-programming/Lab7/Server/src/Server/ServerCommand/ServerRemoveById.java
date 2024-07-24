package Server.ServerCommand;

import MajorClasses.Printer;
import db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerRemoveById extends ServerCommand {

    private static final String REMOVE_COOR = "DELETE FROM coordinates where fk_product_id = ?";
    private static final String REMOVE_PERSON = "DELETE FROM person where fk_product_id = ?";
    private static final String REMOVE_PRODUCT = "DELETE FROM product where product_id = ?";
    private static final String GET_OBJ_USER = "SELECT fk_user_id FROM product where product_id = ?";
    boolean flag = false;
    // тут можно не брать в кавычки потому что чиселки)))
    public void execute(DatabaseHandler databaseHandler) {
        try {
            PreparedStatement statement555 = databaseHandler.connection.prepareStatement(GET_OBJ_USER);
            statement555.setInt(1, product.getId());
            ResultSet resultSet = statement555.executeQuery();
            resultSet.next();
            int obj_user_id = resultSet.getInt("fk_user_id");
            if (obj_user_id != databaseHandler.getUserId()) {
                System.out.println("Чужой файл, нет доступа");
                flag = true;
            }
        } catch (SQLException e) {
            Printer.printSQLException();
        }
        if (!flag) {
            try {
                PreparedStatement statement = databaseHandler.connection.prepareStatement(REMOVE_COOR);
                statement.setInt(1, product.getId());
                statement.execute();
                statement.close();

                statement = databaseHandler.connection.prepareStatement(REMOVE_PERSON);
                statement.setInt(1, product.getId());
                statement.execute();
                statement.close();

                statement = databaseHandler.connection.prepareStatement(REMOVE_PRODUCT);
                statement.setInt(1, product.getId());
                statement.execute();
                statement.close();

                System.out.println("Элемент успешно удален");
            } catch (SQLException e) {
                Printer.printSQLException();
            }
        }
    }
}
