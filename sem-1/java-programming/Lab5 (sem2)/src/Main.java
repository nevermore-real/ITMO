import MajorClasses.Application;

//Запуск через терминал ))
// java -cp out\production\Lab5 Main save.yaml
// java -cp out/production/Lab5 Main save.yaml    Windows же !)


//Everything is ready! Go!!!

public class Main {
    public static void main(String[] args) {

        Application application = new Application(args);
        application.start();

    }
}