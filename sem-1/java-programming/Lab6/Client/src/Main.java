import MajorClasses.Loader;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.connectToServer(args);
        if (client.connection) {
            client.startFlag = true;
            client.startWorking();
        }
    }
}
