package MajorClasses;

import MyExceptions.InputFileException;
import MyExceptions.WrongArgumentException;
import MyExceptions.WrongInputException;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Loader {

    public static boolean FILE_IS_OK = true;
    public static final String SAVE_ENV_VAR = "savePath";
    public static String filename;
    public static ArrayList<String> historyArray = new ArrayList<>();

    public void loadCollection(ArrayDeque<Product> collection, String[] args) throws WrongInputException, InputFileException {

        if(args == null || args.length == 0){
            Map env = System.getenv();
            filename = (String) env.get(SAVE_ENV_VAR);
            if (filename == null){
                System.out.println("Не был введен загружаемый файл -- не работает команда Save. Коллекция изначально пуста.");
                FILE_IS_OK = false;
            }
        } else if (args.length != 1) {
            throw new WrongInputException("Слишком много аргументов на вход");

        } else {
            Map env = System.getenv();
            filename = (String) env.get(args[0]);

            if (filename == null) {
                System.out.println("Нет такой переменной окружения");
                File file = new File(args[0]);
                if (file.exists() && !file.isDirectory()) {
                    filename = args[0]; // Передали не переменную окружения, а само имя файла

                } else {
                    System.out.println("Входящий файл не существует. Команда Save не будет работать. Коллекция изначально пуста");
                    FILE_IS_OK = false;
                }
            }
        }
        if (FILE_IS_OK){
            File file = new File(filename);

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                String[] line;
                String value;
                Product product;
                Set<Integer> idSet = new HashSet<>();
                while (reader.readLine() != null) { // ---

                    reader.readLine(); // number

                    product = new Product();

                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; //id

                    try {
                        if (Validator.validateIntMoreThanZero(value)) {
                            if (idSet.contains(Integer.parseInt(value))) throw new InputFileException("Повтор id");
                            else idSet.add(Integer.parseInt(value));
                            product.setId(Integer.parseInt(value));
                        } else throw new InputFileException("Неправильный id");
                    }catch (NumberFormatException e){
                        throw new InputFileException("Не целый id");
                    }
                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; //name
                    if (value != "" && value != null && !value.equalsIgnoreCase("null"))product.setName(value);
                    else throw new InputFileException("Неправильное имя");

                    reader.readLine(); // Coordinates
                    Coordinates coor = new Coordinates();

                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; // x
                    if (Validator.validateDouble(value) && Double.parseDouble(value) > Coordinates.COORDINATES_LEFT_BOUND)coor.setX(new BigDecimal(value));
                    else throw new InputFileException("Неверный икс");
                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; // y
                    if (Validator.validateDouble(value) && Double.parseDouble(value) <= Coordinates.COORDINATES_RIGHT_BOUND)coor.setY(Long.parseLong(value));
                    else throw new InputFileException("Неверный игрек");
                    product.setCoordinates(coor);

                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; // creationDate
                    try {
                        product.setCreationDate(java.time.LocalDate.parse(value));
                    }catch (Exception e){throw new InputFileException("Неверная дата");}
                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; // price
                    if (Validator.validateDouble(value) && Double.parseDouble(value) > 0) product.setPrice(new BigDecimal(value));
                    else throw new InputFileException("Неправильная цена");
                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; // partNumber
                    if (value!= "" && value != null)product.setPartNumber(value);
                    else throw new InputFileException("Неверный парт намбер");

                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; // UnitOfMeasure
                    if (UnitOfMeasure.getValue(value) != null) product.setUnitOfMeasure(UnitOfMeasure.getValue(value));
                    else throw new InputFileException("Неверные единицы измерения");

                    reader.readLine(); // Person
                    Person owner = new Person();

                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; // name
                    if (value != "" && value != null && !value.equalsIgnoreCase("null"))owner.setName(value);
                    else throw new InputFileException("Неправильное имя человека");

                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; // height
                    if (Validator.validateInt(value) && Integer.parseInt(value) > 0) owner.setHeight(Long.parseLong(value));
                    else throw new InputFileException("Неверный рост");
                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; // weight
                    if (Validator.validateInt(value) && Integer.parseInt(value) > 0) owner.setWeight(Long.parseLong(value));
                    else throw new InputFileException("Неверный вес");
                    line = reader.readLine().replaceAll("\\s+", "").split(":");
                    value = line[1]; // Color

                    if (Color.getValue(value) != null) owner.setEyeColor(Color.valueOf(value));
                    else throw new InputFileException("Неверный цвет глаз");

                    product.setPerson(owner);

                    reader.readLine(); // ---

                    collection.add(product);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл не существует или не может быть прочитан");

            }catch (WrongArgumentException e){
                System.out.println(e.getMessage());

            } catch (IOException e) {
                System.out.println("IOException во время чтения файла");
            }
        }
    }
    public void makeHistory(String commWithArgs){

            final int HISTORY_CONST = 13;

            String command = commWithArgs.split(" ")[0];

            if (historyArray.size() <= HISTORY_CONST){
                historyArray.add(command);
            }else{
                historyArray.remove(0);
                historyArray.add(command);
            }
    }
}
