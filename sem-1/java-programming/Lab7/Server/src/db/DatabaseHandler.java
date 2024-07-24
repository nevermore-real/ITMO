package db;

import MajorClasses.*;
import MajorClasses.Color;
import MyExceptions.WrongArgumentException;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayDeque;

public class DatabaseHandler {
    public Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/studs";
    private String user = "s367619";
    //ssh -p 2222 s367619@se.ifmo.ru -L 5432:pg:5432
    private String password = "h3kRcY4X6CqJGiFU";
    private int userId;
    private String userLogin;
    private String userPassword;
    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String LOGIN = "SELECT * FROM users WHERE login = ? AND password = ?;";
    private static final String GET_ID = "SELECT id FROM users WHERE login = ? AND password = ?;";

    private static final String INSERT_NEW_USER = "INSERT INTO users VALUES(nextval('seq'), ?, ?)";

    public int connectToDataBase() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url, user, password);
            Printer.printDataBaseConnected();
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            Printer.printDataBaseNotConnected();
            return 0;
        }
    }
    public void setUserId(int id){
        this.userId = id;
    }
    public int getUserId(){
        return this.userId;
    }
    public void setUserLogin(String login){
        this.userLogin = login;
    }
    public void setUserPassword(String password){
        this.userPassword = password;
    }

    public int getUserIdByLogin(String login, String password){
        try {
            PreparedStatement statement = connection.prepareStatement(LOGIN);
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                statement.close();
                return id;

            }
            return 0;
        } catch (SQLException e) {
            Printer.printWrongUser();
            return 0;
        }
    }
    public int registerNewUser(){
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER);
            statement.setString(1, userLogin);
            statement.setString(2, userPassword);
            statement.execute();
            statement.close();
            PreparedStatement statement1 = connection.prepareStatement(GET_ID);
            statement1.setString(1, userLogin);
            statement1.setString(2, userPassword);
            ResultSet resultSet = statement1.executeQuery();
            if (resultSet.next()){
                Printer.printRegistration();
                int id = resultSet.getInt("id");
                statement1.close();
                return id;
            }
            statement1.close();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            Printer.printRegistrationError();
            return 0;
        }
    }
    public ArrayDeque<Product> loadCollection(){
        ArrayDeque<Product> collection = new ArrayDeque<>();
        final String SEL_PRODUCT = "SELECT * FROM product";
        final String SEL_PERSON = "SELECT * FROM person WHERE fk_product_id = ?";
        final String SEL_COOR= "SELECT * FROM coordinates WHERE fk_product_id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(SEL_PRODUCT);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            int product_id, personId;
            String name, personName;
            LocalDate date;
            float price, height, weight, x, y;
            String partNumber;
            UnitOfMeasure unitOfMeasure;
            Color eyecolor;
            while (resultSet.next()){
                Product product = new Product();
                product_id = resultSet.getInt("product_id");
                name = resultSet.getString("name");
                date = LocalDate.parse(resultSet.getString("creation_date"));
                price = resultSet.getFloat("price");
                partNumber = resultSet.getString("part_number");
                unitOfMeasure = UnitOfMeasure.getValue(resultSet.getString("unit_of_measure"));
                product.setId(product_id);
                product.setCreationDate(date);
                product.setPartNumber(partNumber);
                product.setPrice(BigDecimal.valueOf(price));
                product.setName(name);
                product.setUnitOfMeasure(unitOfMeasure);

                Person person = new Person();
                PreparedStatement statement1 = connection.prepareStatement(SEL_PERSON);
                statement1.setInt(1, product_id);
                statement1.executeQuery();
                ResultSet resultSet1 = statement1.getResultSet();
                resultSet1.next();
                personName = resultSet1.getString("name");
                weight = resultSet1.getFloat("weight");
                height = resultSet1.getFloat("height");
                eyecolor = Color.valueOf(resultSet1.getString("eye_color"));
                person.setName(personName);
                person.setHeight((long) height);
                person.setHeight((long) weight);
                person.setEyeColor(eyecolor);
                product.setPerson(person);
                statement1.close();

                Coordinates coordinates = new Coordinates();
                statement1 = connection.prepareStatement(SEL_COOR);
                statement1.setInt(1, product_id);
                statement1.executeQuery();
                resultSet1 = statement1.getResultSet();
                resultSet1.next();
                x = resultSet1.getFloat("x");
                y = resultSet1.getFloat("y");
                coordinates.setX(BigDecimal.valueOf(x));
                coordinates.setY((long) y);
                product.setCoordinates(coordinates);
                statement1.close();
                collection.add(product);

            }
            return collection;
        }catch (SQLException | WrongArgumentException e){
            Printer.printSQLException();
            return null;
        }
    }

}

//    create table product
//        (product_id integer primary key,
//         fk_user_id integer references users(id),
//    name text not null,
//        creation_date text not null,
//        price float,
//        part_number text not null,
//        unit_of_measure text not null
//        );