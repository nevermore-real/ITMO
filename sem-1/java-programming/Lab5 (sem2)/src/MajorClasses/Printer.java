package MajorClasses;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Printer {

    private static final int HISTORY_COMMANDS_AMOUNT = 14;
    public static void printRemoveAll(boolean result){
        System.out.println(result ? "Все продукты данного владельца успешно удалены!" :
                "У данного владельца нет ни одного продукта");
    }
    public static void printAdd(boolean result){
        System.out.println(result ? "Элемент успешно добавлен!" :
                "Ошибка при добавлении");
    }
    public static void printAddMin(boolean fail){
        System.out.println("Элемент не самый маленький -- добавление не произошло");
    }
    public static void printClear(boolean success){
        System.out.println("Коллекция теперь пуста.");
    }
    public static void printExit(boolean success){
        System.out.println("Программа завершила работу, всего хорошего!");
    }
    public static void printGroup(boolean success, ArrayList<String> list, ArrayList<Integer> countList){
        for (int i = 0; i < countList.size(); i++){
            System.out.println("Элементов с номером " + list.get(i) + " в коллекции -- " + countList.get(i));
        }
    }
    public static void printShow(boolean success, ArrayDeque<Product> collection){

        int cnt = 1;
        for (Product element : collection){
            System.out.println("----------");
            System.out.println(cnt++ + ":");
            System.out.println(element.toString());
            System.out.println("----------");
        }
    }

    public static void printRemove(boolean result){
        System.out.println(result ? "Элемент успешно удален!" :
                "Элемент в коллекции не найден");
    }
    public static void printRemoveLower(boolean result){
        System.out.println(result ? "Все меньшие элементы успешно удалены!" :
                "Меньших элементов в коллекции нет");
    }
    public static void printSave(boolean result){
        System.out.println(result ? "Файл успешно сохранен!" :
                "Файл сохранения не найден");
    }
    public static void printHistory(boolean success){
        System.out.println("История последних " + HISTORY_COMMANDS_AMOUNT + " команд:");
        for (String historyCommand : Loader.historyArray){
            System.out.println(historyCommand);
        }
    }

    public static void printInfo(boolean success, ArrayDeque<Product> collection){

        Class<?> elementType = collection.getFirst().getClass();
        Class<?> collectionType = collection.getClass();
        Class<?> collectionSuperType = collectionType.getSuperclass();

        System.out.println("Информация о коллекции");
        System.out.println("Тип коллекции: " + collectionType);
        System.out.println("Коллекция является реализацией: " + collectionSuperType);
        System.out.println("Тип хранимых элементов: " + elementType);
        System.out.println("Дата инициализации: " + Application.CREATION_DATE);
        System.out.println("Количество объектов: " + collection.size());
    }
    public static void printValidProductFields(int messageCase){
        switch (messageCase){
            case 1:
                System.out.println("Введите имя (не пустое):");
            case 2:
                System.out.println("Имя не может быть пустым!");
            case 3:
                System.out.println("Имя не может быть null :)");
            case 4:
                System.out.println("Введите цену больше нуля:");
            case 5:
                System.out.println("Введите partNumber длиной не меньше 15");
            case 6:
                System.out.println("Введите цену больше нуля");
            case 7:
                System.out.println("пустой партНамбер");
            case 8:
                System.out.println("Парт намбер длиной не меньше 15");
        }
    }
    public static void printValidCoordinates(int messageCase){
        switch (messageCase){
            case 1:
                System.out.println("Введите x > " + Coordinates.COORDINATES_LEFT_BOUND);
            case 2:
                System.out.println("Введите y <= " + Coordinates.COORDINATES_RIGHT_BOUND);
            case 3:
                System.out.println("Неправильный x");
            case 4:
                System.out.println("Неправильный y");
        }
    }

    public static void printValidUnitOfMeasure(){
        System.out.println("Выберите единицу измерения:\n" +
                "1. SQUARE_METERS\n" +
                "2. MILLILITERS\n" +
                "3. GRAMS");
    }

    public static void printValidPersonFields(int messageCase){
        switch (messageCase) {
            case 1:
                System.out.println("Введите имя (не пустое)");
            case 2:
                System.out.println("Имя не может быть пустым!");
            case 3:
                System.out.println("Имя не может быть null :)");
            case 4:
                System.out.println("Введите рост > 0");
            case 5:
                System.out.println("Введите вес > 0");
            case 6:
                System.out.println("Это целое число больше нуля");
            case 7:
                System.out.println("Введите цвет глаза: +\n" +
                        "1. RED \n" +
                        "2. BLACK \n" +
                        "3. YELLOW \n" +
                        "4. BROWN");
            case 8:
                System.out.println("Нет такого цвета ...");
        }
    }

    public static void printValidEasy(int messageCase){
        switch (messageCase){
            case 1:
                System.out.println("Это составное поле. Его название вводится отдельно");
            case 2:
                System.out.println("это поле составного типа - сначала надо ввести тип");
            case 3:
                System.out.println("Ну нет такого поля!");
            case 4:
                System.out.println("Нет таких единиц измерения");
            case 5:
                System.out.println("Цена должна быть числом больше нуля");
        }
    }

    public static void printValidType(int messageCase){
        switch (messageCase){
            case 1:
                System.out.println("Пустой тип");
            case 2:
                System.out.println("Введите x / y");
            case 3:
                System.out.println("Введите name / height / weight / eyecolor");
            case 4:
                System.out.println("Какой-то мутный тип!! (Или не составной)");
        }
    }

    public static void printValidHard(int messageCase){
        switch (messageCase){
            case 1:
                System.out.println("Неправильный ввод координат");
            case 2:
                System.out.println("Неверный ввод полей человека");
            case 3:
                System.out.println("Странные параметры для человека...");
            case 4:
                System.out.println("Нет такого цвета глаз!");
        }
    }
    public static void printUpdate(int messageCase){
        switch (messageCase){
            case 1:
                System.out.println("Введите название поля и новое значение через равно, если поле составное - только название");
            case 2:
                System.out.println("Поле успешно изменено!");
                System.out.println("Введите следующее поле, или stop чтобы завершить");
                System.out.println();
            case 3:
                System.out.println("Введите поле или stop чтобы закончить");
            case 4:
                System.out.println("Поля успешно изменены! Невероятно!!");
                System.out.println("Введите следующее поле или stop чтобы закончить");
                System.out.println();
        }
    }
    public static void printValidById(int messageCase){
        switch (messageCase){
            case 1:
                System.out.println("Пожалуйста, введите id элемента");
            case 2:
                System.out.println("Элемент с таким id не найден. Пожалуйста, попробуйте еще раз.");
        }
    }
    public static void printWrongX(){
        System.out.println("Неправильный икс");
    }
    public static void printWrongY(){
        System.out.println("Неправильный игрек");
    }

    public static void printValidateInt(int messageCase){
        switch (messageCase){
            case 1:
                System.out.println("Число слишком маленькое!");
            case 2:
                System.out.println("Число слишком большое!");
        }
    }
    public static void printTryAgain(){
        System.out.println("-____- Попробуйте еще раз");
    }
    public static void printStopToExit(){
        System.out.println("Чтобы выйти, введите stop");
    }
    public static void printOneArgumentNeeded(){
        System.out.println("Введите один аргумент");
    }
    public static void printWrongInput(){
        System.out.println("Неправильный ввод");
    }
    public static void printIOException(){
        System.out.println("IOException");
    }
    public static void printNumberFormatException(){
        System.out.println("Введите число . . .");
    }
}
