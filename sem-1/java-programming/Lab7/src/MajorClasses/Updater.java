package MajorClasses;


import Commands.CommandBox;
import MyExceptions.ExecuteException;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class Updater {

    public CommandBox update(Product product, BufferedReader reader) throws ExecuteException{

        Printer.printUpdate("start");
        CommandBox box = new CommandBox("UpdateId");

        while (true) {
            try {
                String[] splitOne = reader.readLine().replaceAll("[,; ]", "").toLowerCase().split("=");
                if (splitOne.length == 1 && "stop".equals(splitOne[0])) throw new ExecuteException("Завершение команды");
                if (splitOne.length == 1) { // [coordinates]
                    String type = splitOne[0];
                    if (Validator.validType(type)) {
                        String[] args = getField(type, reader);
                        if (args != null){
                            box.setField(type);
                            box.setMassiveArgs(args);
                            box.setProduct(product);
                            return box;
                            //updHardField
                        }else Printer.printUpdate("enterNewField");
                    }
                } else if (splitOne.length == 2){ // [x, 1]

                    if (Validator.validEasyUpdate(splitOne)) {
                        box.setProduct(product);
                        box.setMassiveArgs(splitOne);
                        return box;
                        //updEasyField
                    }
                }
            }catch (IOException e){
                Printer.printIOException();
            }
        }
    }
    public static void updEasyField(Product product, String[] args) throws ExecuteException {
        String field = args[0];

        try {
            Class<?> type = String.class;
            if (field.equals("price")) type = BigDecimal.class;
            else if (field.equals("unitofmeasure")){
                type = UnitOfMeasure.class;
                field = "unitOfMeasure";
            }

            Method updField = Product.class.getMethod("set" + field.substring(0, 1).toUpperCase() + field.substring(1), type);

            if (field.equals("price")) {
                BigDecimal value = new BigDecimal(args[1]);
                updField.invoke(product, value);
            } else if (field.equals("unitOfMeasure")) {
                UnitOfMeasure value = UnitOfMeasure.getValue(args[1]);
                updField.invoke(product, value);
            } else {
                String value = args[1];
                updField.invoke(product, value);
            }
            Printer.printUpdate("fieldsUpdated");
        } catch (Exception e) {
            throw new ExecuteException("Что-то не так с апдейтом...");
        }
    }
    public static void updHardField(String type, String[] args, Product product) throws ExecuteException{
        try {
            Method method = Product.class.getMethod("get" + type.substring(0, 1).toUpperCase() + type.substring(1));
            Object classField = method.invoke(product); // ex. Coordinates coors =  getCoordinates() // not Class<?> classField!!!

            String field = args[0];
            Class<?> argtype = String.class;
            if (field.equals("x")) argtype = BigDecimal.class;
            else if ("y, height, weight".contains(field)) argtype = long.class;
            else if (field.equals("eyecolor")) {
                argtype = Color.class;
                field = "eyeColor";
            }
            Method method2 = classField.getClass().getMethod("set" + field.substring(0, 1).toUpperCase() + field.substring(1), argtype); //setX()
            if (field.equals("x")) {
                BigDecimal value = new BigDecimal(args[1]);
                method2.invoke(classField, value);

            } else if ("y, height, weight".contains(field)) {
                long value = Long.parseLong(args[1]);
                method2.invoke(classField, value);

            } else if (field.equals("eyeColor")) {
                Color value = Color.getValue(args[1]);
                method2.invoke(classField, value);
            } else {
                String value = args[1];
                method2.invoke(classField, value);
            }
            Printer.printUpdate("fieldUpdated");
        } catch (IllegalAccessException e) {
            throw new ExecuteException("Ошибка доступа");
        } catch (InvocationTargetException e) {
            throw new ExecuteException("Ошибка при вызове метода -- рефлексии");
        } catch (NoSuchMethodException e) {
            throw new ExecuteException("Нет такого метода -- рефлексия");
        }
    }
    public String[] getField(String type, BufferedReader reader){
        try{
            String[] args = reader.readLine().replaceAll("[,; ]", "").toLowerCase().split("=");
            if (Validator.validHardField(type, args)){
                return args;
            }else return null;
        }catch (IOException e){
            System.out.println("IO exception");
            return null;
        }
    }
}
