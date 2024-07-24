import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import MajorClasses.*;
import Commands.CommandBox;
import MyExceptions.ExecuteException;

public class Client {

    private ArrayDeque<Product> collection;
    private int port;
    private static final String PORT_ENV_VAR = "port";
    private String host = "localhost";
    private Application application;
    private CommandBox box;
    private ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
    private SocketChannel client;
    public boolean connection = false;
    private boolean tryingToReconnect = false;
    private int reconnections = 0;
    private static final int MAX_RECONNECTIONS = 5;
    boolean startFlag = false;
    boolean callbackFlag = false;
    static int recurcyCnt = 0;

    public void connectToServer(String[] args) {

        Map env = System.getenv();
        String portname = (String) env.get(PORT_ENV_VAR);
        //CollectionLoader.load(collection, args);
        if (portname == null) {
            Printer.printPortIsMissing();
        } else {
            port = Integer.parseInt((String) env.get(PORT_ENV_VAR));
            Printer.printClientStart();
            connect();
        }
    }
    public void passAuth(){
        while (true) {
            String login, password;
            Scanner scanner = new Scanner(System.in);
            Printer.printEnterLogin();
            login = scanner.nextLine();
            Printer.printEnterPassword();
            password = scanner.nextLine();
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(login);
            arrayList.add(password);
            box = new CommandBox("auth");
            box.setArrayArgs(arrayList);
            writeMessageToServer(true);
            readServerResponse();
            if (callbackFlag) {
                String yesOrNo = scanner.nextLine();
                if (yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("y")) {
                    box = new CommandBox("createNewUser");
                    writeMessageToServer(true);
                    readServerResponse();
                    break;
                }else{
                    System.out.println("Пожалуйста, авторизуйтесь для выполнения команд");
                }

            }else{
                break;
            }
        }
    }
    public void startWorking(){
        application = new Application(collection);
        while (true){
            writeMessageToServer(false);
            if (connection) {
                readServerResponse();
            }else{
                connect();
            }
        }
    }

    public void readServerResponse(){
        try {
        client.read(buffer);
        buffer.clear();
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Response response = (Response) ois.readObject();
        callbackFlag = response.flag;
        if (response.getCollection()!= null && application!=null){
            application.updateCol(response.getCollection());
        }
        response.printMessage();

        } catch (ClassNotFoundException e){
        Printer.printClassNotFound();
        } catch (IOException e){
            Printer.printClientReadError();
        }
    }
    public void writeMessageToServer(boolean authFlag) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            if (!authFlag) {
                if (!tryingToReconnect) box = application.executeOneCommand();

                while (box == null) {
                    box = application.executeOneCommand();
                }
            }

            oos.writeObject(box);
            client.write(ByteBuffer.wrap(baos.toByteArray()));

        } catch (IOException e) {
            Printer.printClientWriteError();
            connection = false;
            tryToReconnect();
            if (connection){
                writeMessageToServer(authFlag);
                tryingToReconnect = false;
            }
        }
    }
    public void connect(){
        try {
            client = SocketChannel.open(new InetSocketAddress(host, port));
            connection = true;
            reconnections = 0;
            if (!startFlag) tryingToReconnect = false;
            Printer.printConnectionEstablished();
        }catch (IOException e){
            if (!tryingToReconnect) Printer.printConnectionIsNotEstablished();
            else Printer.printReconnectionFailed();
            tryToReconnect();
        }
    }
    public void tryToReconnect(){
        tryingToReconnect = true;
        if (reconnections < MAX_RECONNECTIONS) Printer.printReconnectionAttemp();
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Printer.printIntException();
        }
        if (reconnections++ < MAX_RECONNECTIONS){
            connect();
        }else{
            Printer.printExceededMaxReconnectionTries();
            System.exit(0);
        }
    }
}
