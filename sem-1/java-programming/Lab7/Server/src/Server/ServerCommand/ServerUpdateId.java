package Server.ServerCommand;

import MajorClasses.Printer;
import MajorClasses.Updater;
import MyExceptions.MyException;
import db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServerUpdateId extends ServerCommand {
    
    public void execute(DatabaseHandler databaseHandler){
        try {
            if (box.getField() != null) {
                Updater.updHardField(someField, massiveArgs, product);
                int id = product.getId();
                String perem = massiveArgs[0];
                String value = massiveArgs[1];
                String stmt1 = "UPDATE " + someField.toLowerCase() + " SET " + perem +" = '" + value +"' WHERE fk_product_id = " + product.getId();
                // нужно значение взять в одинарные кавычки!!!!!!!!!!!!
                try{
                    PreparedStatement statement = databaseHandler.connection.prepareStatement(stmt1);

                    System.out.println(statement);

                    statement.execute();
                    statement.close();

                }catch (SQLException e){
                    e.printStackTrace();
                    Printer.printSQLException();
                }
            } else {
                Updater.updEasyField(product, massiveArgs);
                int id = product.getId();
                String perem = massiveArgs[0];
                String value = massiveArgs[1];
                String stmt2 = "UPDATE product SET " + perem + " = '" + value + "'" +  " WHERE product_id = " + product.getId();
                // нужно значение взять в одинарные кавычки!!!!!!!!!!!!
                try{
                    PreparedStatement statement = databaseHandler.connection.prepareStatement(stmt2);

                    System.out.println(statement);

                    statement.execute();
                    statement.close();

                }catch (SQLException e){
                    e.printStackTrace();
                    Printer.printSQLException();
                }

            }
        }catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
