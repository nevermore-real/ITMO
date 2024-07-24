package MajorClasses;

import MyExceptions.ExecuteException;
import MyExceptions.WrongInputException;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Validator {

    public BigDecimal price;
    private final BufferedReader reader;

    public Validator(BufferedReader reader) {
        this.reader = reader;
    }

    public ArrayList<String> getValidProductFields(ArrayList<String> arr) throws ExecuteException {

        ArrayList<String> resultArgs = new ArrayList<>();
        String[] args;
        Printer.printValidProductFields(1);

        int counter = 1;

        while (counter < 4) {

            try {
                args = reader.readLine().replaceAll(",",".").replaceAll("^\\s+","").split(" ");
                if (args.length != 1) {
                    Printer.printOneArgumentNeeded();
                } else if (args[0].equals("stop")) throw new ExecuteException("Сброс команды");
                else {
                    String value = args[0];
                    switch (counter) {
                        case 1:
                            if (value.equals("")) {
                                Printer.printValidProductFields(2);
                            } else if (value.equalsIgnoreCase("null")) {
                                Printer.printValidProductFields(3);
                            } else {
                                counter += 1;
                                resultArgs.add(value);
                                if (this.price == null) Printer.printValidProductFields(4);
                                else{
                                    counter += 1;
                                    Printer.printValidProductFields(5);
                                }
                            }
                            break;
                        case 2:
                            if (!validateDoubleMoreThatZero(value)) Printer.printValidProductFields(6);
                            else {
                                counter += 1;
                                resultArgs.add(value);
                                Printer.printValidProductFields(5);
                            }
                            break;
                        case 3:
                            if (value.equals("")) Printer.printValidProductFields(7);
                            else if (value.length() < 15) Printer.printValidProductFields(8);
                            else{
                                counter +=1;
                                resultArgs.add(value);
                            }
                    }
                }
            } catch (IOException e) {
                Printer.printIOException();
            }
        }
        return resultArgs;
    }

    public ArrayList<String> getValidCoordinates() throws ExecuteException {

       Printer.printValidCoordinates(1);

        ArrayList<String> resultArgs = new ArrayList<>();
        String[] args;
        int counter = 1;
        while (counter < 3) {

            try {
                args = reader.readLine().replaceAll(",",".").replaceAll("^\\s*","").split(" ");
                if (args.length == 1 && args[0].equals("stop")) throw new ExecuteException("Сброс команды");
                else if (args.length != 1) Printer.printWrongInput();
                else {
                    String value = args[0];
                    switch (counter){
                        case 1:
                            if (validateDouble(value) && (new BigDecimal(value).compareTo(new BigDecimal(Coordinates.COORDINATES_LEFT_BOUND)) > 0)){
                                counter +=1;
                                resultArgs.add(value);
                                Printer.printValidCoordinates(2);
                            } else {
                                Printer.printValidCoordinates(3);
                            }
                            break;
                        case 2:
                            if (validateInt(value) && (Long.parseLong(value) <= Coordinates.COORDINATES_RIGHT_BOUND)){
                                counter +=1;
                                resultArgs.add(value);
                            }else{
                                Printer.printValidCoordinates(4);
                            }
                    }
                }
            } catch (IOException e) {
                Printer.printIOException();
            }
        }return resultArgs;
    }

    public UnitOfMeasure getValidUnitOfMeasure() throws ExecuteException {
        Printer.printValidUnitOfMeasure();
        while (true) {
            try {
                String input = reader.readLine().replaceAll(",",".").replaceAll("^\\s*","");
                UnitOfMeasure unit;
                if ((unit = UnitOfMeasure.getValue(input)) != null) {
                    return unit;
                } else {
                    if (input.equalsIgnoreCase("stop")) throw new ExecuteException("Сброс команды");
                    Printer.printTryAgain();
                    Printer.printValidUnitOfMeasure();
                    Printer.printStopToExit();
                }
            } catch (IOException e) {
                Printer.printIOException();
            }
        }
    }

    public ArrayList<String> getValidPersonParameters() throws ExecuteException {

        ArrayList<String> resultArgs = new ArrayList<>();

        Printer.printValidPersonFields(1);

        int counter = 1;

        while (counter < 5) {

            try {
                String[] args = reader.readLine().replaceAll(",",".").replaceAll("^\\s*","").split(" ");
                if (args.length != 1) {
                    Printer.printOneArgumentNeeded();
                } else if (args[0].equals("stop")) throw new ExecuteException("Сброс команды");
                else {
                    String value = args[0];
                    switch (counter) {
                        case 1:
                            if (value.equals("")) {
                                Printer.printValidPersonFields(2);
                            } else if (value.equalsIgnoreCase("null")) {
                                Printer.printValidPersonFields(3);
                            } else {
                                counter += 1;
                                resultArgs.add(value);
                                Printer.printValidPersonFields(4);
                            }
                            break;
                        case 2, 3:
                            if (!validateInt(value) || Integer.parseInt(value) <= 0) {
                                if (counter == 2) {
                                    Printer.printValidPersonFields(4);
                                } else {
                                    Printer.printValidPersonFields(5);
                                }
                                Printer.printValidPersonFields(6);
                            }else {
                                counter += 1;
                                resultArgs.add(value);
                                if (counter == 3) {
                                    Printer.printValidPersonFields(5);
                                } else {
                                    Printer.printValidPersonFields(7);
                                }
                            }
                            break;
                        case 4:
                            Color unit;
                            if ((unit = Color.getValue(value)) != null) {
                                resultArgs.add(unit.toString());
                                counter += 1;
                            } else {
                                if (value.equalsIgnoreCase("stop")) throw new ExecuteException("Сброс команды");
                                Printer.printValidPersonFields(8);
                                Printer.printValidPersonFields(7);
                                Printer.printStopToExit();
                            }
                    }
                }
            } catch (IOException e) {
                Printer.printIOException();
            }
        }
        return resultArgs;
    }

    public static boolean validEasyUpdate(String[] values) {

        if ("coordinates, person".contains(values[0])) {
            Printer.printValidEasy(1);
            return false;
        } else if (!Arrays.asList(Product.PRODUCT_FIELDS).contains(values[0])){
            if (Arrays.asList(Product.PRODUCT_COMPLEX_FIELDS).contains(values[0])){
                Printer.printValidEasy(2);
            }else Printer.printValidEasy(3);
            return false;
        } else if (values[0].equals("unitofmeasure") && (UnitOfMeasure.getValue(values[1]) == null)) {
            Printer.printValidEasy(4);
            return false;
        } else if (values[0].equals("price") && (!validateDoubleMoreThatZero(values[1]))) {
            Printer.printValidEasy(5);
            return false;
        }
        return true;
    }

    public static boolean validType(String type) {

        if (type == null || type.equals("")){
            Printer.printValidType(1);
            return false;
        }
        if ("coordinates".equals(type)){
            Printer.printValidType(2);
            return true;
        }
        if ("person".equals(type)){
            Printer.printValidType(3);
            return true;
        }
        Printer.printValidType(4);
        return false;
    }
    public static boolean validHardField(String type, String[] args){
        if (args.length != 2) {
            Printer.printWrongInput();
            return false;
        }
        String field = args[0];
        String value = args[1];
        if ("coordinates".equals(type)){
            if (field.equals("x")) return validateX(value);
            if (field.equals("y")) return validateY(value);
            Printer.printValidHard(1);
            return false;
        }
        if ("person".equals(type)){
            if (!Arrays.asList(Person.PERSON_FIELDS).contains(field)){
                Printer.printValidHard(2);
                return false;
            }if ("height,weight".contains(field) && (!validateIntMoreThanZero(value))){
                Printer.printValidHard(3);
                return false;

            }if (field.equals("eyecolor") && (Color.getValue(value) == null)) {
                Printer.printValidHard(4);
                return false;
            }
        }
        return true;
    }
    public static boolean validateX(String x){

        if (!validateDouble(x) || Double.parseDouble(x) <= Coordinates.COORDINATES_LEFT_BOUND){
            Printer.printWrongX();
            return false;

        }return true;
    }
    public static boolean validateY(String y){

        if(!validateInt(y) || Integer.parseInt(y) > Coordinates.COORDINATES_RIGHT_BOUND){
            Printer.printWrongY();
            return false;

        }return true;
    }
    public static boolean validateDoubleMoreThatZero(String num){

         if (!validateDouble(num) || Double.parseDouble(num) <= 0){
            return false;

        }return true;
    }
    public static boolean validateIntMoreThanZero(String num){
        if (validateInt(num) && Integer.parseInt(num) > 0){
            return true;
        }return false;
    }
    public static boolean validateDouble(String x){
        try {
            if (Double.parseDouble(x) < (-Math.pow(2,61))){
                Printer.printValidateInt(1);
                return false;

            } else if(Double.parseDouble(x) > (Math.pow(2,61) - 1)){
                Printer.printValidateInt(2);
                return false;

            } return true;

        }catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean validateInt(String x){
        try {
            if (Integer.parseInt(x) < (-Math.pow(2,31))){
                Printer.printValidateInt(1);
                return false;

            }else if(Integer.parseInt(x) > (Math.pow(2,31) - 1)){
                Printer.printValidateInt(2);
                return false;

            } return true;

        }catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean checkMin(BigDecimal inputPrice, ArrayList<String> args, ArrayDeque<Product> collection) throws ExecuteException, WrongInputException{

        if (collection.isEmpty()) throw new ExecuteException("Коллекция-то пуста . . .");
        if (args.size() != 1) throw new WrongInputException("Нужно ввести число - price");
        else {
            try {
                BigDecimal minPrice = collection.getFirst().getPrice();

                for (Product element : collection) {
                    BigDecimal price = element.getPrice();
                    if (minPrice.compareTo(price)> 0) {
                        minPrice = price;
                    }
                }
                return minPrice.compareTo(inputPrice) > 0;

            } catch (NumberFormatException e) {

                Printer.printNumberFormatException();
                return false;
            }
        }
    }
    public static Product getValidProductById(ArrayList<String> arr, BufferedReader reader, ArrayDeque<Product> collection) throws ExecuteException {

        while (true) {
            if (arr == null || arr.size() != 1) Printer.printValidById(1);
            else if (!(validateInt(arr.get(0))));
            else {
                ArrayList<Integer> idlist = new ArrayList<>();
                if (collection.isEmpty()) throw new ExecuteException("Коллекция пуста!");
                else {
                    for (Product product : collection) {
                        idlist.add(product.getId());
                        if (product.getId() == Integer.parseInt(arr.get(0))) {
                            return product;
                        }
                    }
                    Printer.printValidById(2);
                    System.out.printf("Доступные id: %s\n", idlist);
                    Printer.printStopToExit();
                    }
                }
            try {
                arr = new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
                if (arr.get(0).equals("stop")) throw new ExecuteException("Сброс команды");
            }catch (IOException e){
                Printer.printIOException();
            }
        }
    }

    public static boolean checkLowerPrice(ArrayList<String> args, ArrayDeque<Product> collection) throws ExecuteException, WrongInputException {

        throwIfCollectionIsEmpty(collection);

            if (args.size() != 1) throw new WrongInputException("Ошибка ввода. Нужно ввести число - price");
            try {
                BigDecimal inputPrice = new BigDecimal(args.get(0));

                for (Product elem : collection) {
                    BigDecimal price = elem.getPrice();
                    if (inputPrice.compareTo(price) > 0) {
                        return true;
                    }
                }
            } catch (NumberFormatException e) {
                throw new WrongInputException("Ошибка ввода. Нужно ввести число - price");

            } return false;
    }

    public static boolean checkOwnerProducts(ArrayList<String> args, ArrayDeque<Product> collection) throws ExecuteException, WrongInputException {

        throwIfCollectionIsEmpty(collection);

        if (args == null || args.isEmpty()) throw new WrongInputException("Не введен владелец");
        String inputName = args.get(0);
        for (Product elem : collection) {
            if (elem.getPerson().getName().equalsIgnoreCase(inputName)) {
                return true;
            }
        }return false;
}


    public static void throwIfManyArgs(ArrayList<String> args) throws WrongInputException {
         if (args != null && !args.isEmpty()) throw new WrongInputException("Слишком много аргументов");
    }
    public static void throwIfCollectionIsEmpty(ArrayDeque<Product> collection) throws ExecuteException{
        if (collection.isEmpty()) throw new ExecuteException("Коллекция пуста.");
    }
}