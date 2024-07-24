package MajorClasses;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.stream.IntStream;
public class Printer {

    private static final int HISTORY_COMMANDS_AMOUNT = 14;
    private static int cnt = 1;
    private static int reconnectionCnt = 1;
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
        IntStream.range(0,countList.size()).peek(i -> System.out.println("Элементов с номером " + list.get(i) + " в коллекции -- " + countList.get(i))).forEach(i -> {});
//        for (int i = 0; i < countList.size(); i++){
//            System.out.println("Элементов с номером " + list.get(i) + " в коллекции -- " + countList.get(i));
//        }
    }
    public static void printShow(boolean success, ArrayDeque<Product> collection){

        collection.forEach(element -> {
                    System.out.println("----------");
                    Printer.printCnt();
                    System.out.println(element.toString());
                }
                );
    }
    public static void printCnt(){
        System.out.println(cnt + ":");
        cnt++;
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
    public static void printHistory(boolean success, ArrayList<String> args){
        System.out.println("История последних " + HISTORY_COMMANDS_AMOUNT + " команд:");
        for (String historyCommand : args){
            System.out.println(historyCommand);
        }
    }

    public static void printInfo(boolean success, ArrayDeque<Product> collection){
        if (collection.isEmpty()) System.out.println("коллекции нету!");
        else {
            Class<?> elementType = collection.getFirst().getClass();
            Class<?> collectionType = collection.getClass();
            Class<?> collectionSuperType = collectionType.getSuperclass();

            System.out.println("Информация о коллекции");
            System.out.println("Тип коллекции: " + collectionType);
            System.out.println("Коллекция является реализацией: " + collectionSuperType);
            System.out.println("Тип хранимых элементов: " + elementType);
            System.out.println("Дата инициализации: " + LocalDate.now());
            System.out.println("Количество объектов: " + collection.size());
        }
    }
    public static void printValidProductFields(String messageCase){
        switch (messageCase){
            case "enterName" -> System.out.println("Введите имя (не пустое):");
            case "notEmpty" -> System.out.println("Имя не может быть пустым!");
            case "notNull" -> System.out.println("Имя не может быть null :)");
            case "moreThanZero" -> System.out.println("Введите цену больше нуля:");
            case "enterPartNumber" -> System.out.println("Введите partNumber длиной не меньше 15");
            case "enterPrice" -> System.out.println("Введите цену больше нуля");
            case "emptyPartNumber" -> System.out.println("пустой партНамбер");
            case "lenght15" -> System.out.println("Парт намбер длиной не меньше 15");
        }
    }
    public static void printValidCoordinates(String messageCase){
        switch (messageCase){
            case "enterX" -> System.out.println("Введите x > " + Coordinates.COORDINATES_LEFT_BOUND);
            case "enterY" -> System.out.println("Введите y <= " + Coordinates.COORDINATES_RIGHT_BOUND);
            case "wrongX" -> System.out.println("Неправильный x");
            case "wrongY" -> System.out.println("Неправильный y");
        }
    }

    public static void printValidUnitOfMeasure(){
        System.out.println("Выберите единицу измерения:\n" +
                "1. SQUARE_METERS\n" +
                "2. MILLILITERS\n" +
                "3. GRAMS");
    }

    public static void printValidPersonFields(String messageCase){
        switch (messageCase) {
            case "enterName" -> System.out.println("Введите имя (не пустое)");
            case "emptyName" -> System.out.println("Имя не может быть пустым!");
            case "null" -> System.out.println("Имя не может быть null :)");
            case "enterHeight" -> System.out.println("Введите рост > 0");
            case "enterWeight" -> System.out.println("Введите вес > 0");
            case "moreThanZero" -> System.out.println("Это целое число больше нуля");
            case "enterColor" -> System.out.println("Введите цвет глаза: \n" +
                        "1. RED \n" +
                        "2. BLACK \n" +
                        "3. YELLOW \n" +
                        "4. BROWN");
            case "noColor" -> System.out.println("Нет такого цвета ...");
        }
    }

    public static void printValidEasy(String messageCase){
        switch (messageCase){
            case "complexField" -> System.out.println("Это составное поле. Его название вводится отдельно");
            case "enterType" -> System.out.println("это поле составного типа - сначала надо ввести тип");
            case "noField" -> System.out.println("Ну нет такого поля!");
            case "noMeasures" -> System.out.println("Нет таких единиц измерения");
            case "priceMoreThanZero" -> System.out.println("Цена должна быть числом больше нуля");
        }
    }

    public static void printValidType(String messageCase){
        switch (messageCase){
            case "emptyType" -> System.out.println("Пустой тип");
            case "enterXorY"-> System.out.println("Введите x / y");
            case "enterField" -> System.out.println("Введите name / height / weight / eyecolor");
            case "wrongType" -> System.out.println("Какой-то мутный тип!! (Или не составной)");
        }
    }

    public static void printValidHard(String messageCase){
        switch (messageCase){
            case "wrongCoors" -> System.out.println("Неправильный ввод координат");
            case "wrongFields" -> System.out.println("Неверный ввод полей человека");
            case "strangeFields" -> System.out.println("Странные параметры для человека...");
            case "colorNotFound" -> System.out.println("Нет такого цвета глаз!");
        }
    }
    public static void printUpdate(String messageCase){
        switch (messageCase){
            case "start" -> System.out.println("Введите название поля и новое значение через равно, если поле составное - только название");
            case "fieldUpdated" -> System.out.println("Поле успешно изменено!");
            case "enterNewField" -> System.out.println("Введите поле или stop чтобы закончить");
            case "fieldsUpdated" -> System.out.println("Поля успешно изменены! Невероятно!!");
        }
    }
    public static void printValidById(String messageCase){
        switch (messageCase){
            case "enterId"-> System.out.println("Пожалуйста, введите id элемента");
            case "idNotFound" -> System.out.println("Элемент с таким id не найден. Пожалуйста, попробуйте еще раз.");
        }
    }
    public static void printWrongX(){
        System.out.println("Неправильный икс");
    }
    public static void printWrongY(){
        System.out.println("Неправильный игрек");
    }

    public static void printValidateInt(String messageCase){
        switch (messageCase){
            case "tooLow" -> System.out.println("Число слишком маленькое!");
            case "tooBig" -> System.out.println("Число слишком большое!");
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
    public static void printColEmpty(){
        System.out.println("Ошибка во входном файле -- коллекция пуста");
    }
    public static void printIOServerException(){
        System.out.println("Конец связи");
    }
    public static void printConnectionEstablished(){
        System.out.println("Соединение установлено!");
    }
    public static void printConnectionIsNotEstablished(){
        System.out.println("Соединение не установлено.");
    }
    public static void printServerStart(){
        System.out.println("Сервер запущен");
    }
    public static void printClientStart() {
        System.out.println("Клиент запущен");
    }
    public static void printEnterCommand(){
        System.out.println("Введите команду:  //  Введите help для просмотра доступных команд");
    }
    public static void printNumberFormatException(){
        System.out.println("Введите число . . .");
    }
    public static void printSeparator(){
        System.out.println("---");
    }
    public static void printPortIsMissing(){
        System.out.println("Не указан порт подключения");
    }
    public static void printMapClassNotFound(){
        System.out.println("Ошибка в словаре команд");
    }
    public static void printMapReflectionError(){
        System.out.println("Ошибка при рефлексии в мапе");
    }
    public static void printCtrlD(){
        System.out.println("Был брошен Ctrl D, закрываемся :)");
    }
    public static void printWrongCommand(){
        System.out.println("Неправильная команда, попробуйте еще раз.");
    }
    public static void printRealRuntime(){
        System.out.println("Настоящая ошибка при выполнении");
    }
    public static void printRecursionDetected(){
        System.out.println("Прекращение рекурсии!");
    }
    public static void printClassNotFound(){
        System.out.println("Класс не найден, ясен пень");
    }
    public static void printCommandExecuted(String commandName){
        printSeparator();
        System.out.println("Исполнена команда : " + commandName);
        printSeparator();
    }
    public static void print(String s){
        System.out.println(s);
    }
    public static String getErrorMessage(){
        return "\"java.io.BufferedReader.readLine()\" is null";
    }
    public static void printClientReadError(){
        System.out.println("Обишка при чтении с сервера");
    }
    public static void printClientWriteError(){
        System.out.println("Обишка при отправке данных на сервер");
    }
    public static void printReconnectionAttemp(){
        System.out.println("Попытка переподключения через 5 секунд");
    }
    public static void printIntException(){
        System.out.println("Ошибка прерывания потока");
    }
    public static void printExceededMaxReconnectionTries(){
        System.out.println("Превышено количество переподключений. Закрытие программы");
    }
    public static void printReconnectionFailed(){
        System.out.println("Переподключение не удалось" + "..".repeat(reconnectionCnt++));
    }
    public static void printRegistered(){
        System.out.println("Клиент подключен");
    }
    public static void printServerReadException(){
        System.out.println("Клиент отключился");
    }
    public static void printServerWriteException(){
        System.out.println("Ошибка при отправке с сервера");
    }
    public static void printServerRegisterException(){
        System.out.println("Ошибка при подключении клиента");
    }
    public static void printServerStartFailed(){
        System.out.println("Сервер не запущен...");
    }
    public static void printDataBaseConnected(){
        System.out.println("Соединение с базой данных установлено.");
    }
    public static void printDataBaseNotConnected(){
        System.out.println("Соединение с базой данных не установлено...");
    }
    public static void printDriverNotFound(){
        System.out.println("Драйвер управления не найден...");
    }
    public static void printClosedError(){
        System.out.println("Ошибка при закрытии запроса");
    }
    public static void printClosed(){
        System.out.println("Соединение с базой данных разорвано");
    }
    public static void printNotClosed(){
        System.out.println("Ошибка при разрыве соединения...");
    }
    public static void printRollbackError(){
        System.out.println("Произошла ошибка при возврате исходного состояния базы данных");
    }
    public static void printCommitError(){
        System.out.println("Произошла ошибка при коммите состояния базы данных");
    }
    public static void printSavePointError(){
        System.out.println("Ошибка при сохранении состояния базы данных...");
    }
    public static void printEnterPassword(){
        System.out.println("Введите пароль");
    }
    public static void printEnterLogin(){
        System.out.println("Введите логин");
    }
    public static void printWrongPassword(){
        System.out.println("Неправильный пароль");
    }
    public static void printWrongUser(){
        System.out.println("Неверный логин или пароль");
    }
    public static void printRegistrationError(){
        System.out.println("Ошибка при регистрации нового пользователя.");
    }
    public static void printRegistration(){
        System.out.println("Успешная регистрация нового пользователя.");
    }
    public static void printSQLException(){
        System.out.println("Ошибка при работе с базой данных");
    }
}
