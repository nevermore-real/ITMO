import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Map;

import MajorClasses.*;
import Commands.CommandBox;
import MyExceptions.ExecuteException;

public class Client {

    private ArrayDeque<Product> collection = new ArrayDeque<>();
    private int port;
    private static final String PORT_ENV_VAR = "port";
    private String host = "localhost";
    private Application application = new Application(collection);
    private CommandBox box;
    private ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
    private SocketChannel client;
    public boolean connection = false;
    private boolean tryingToReconnect = false;
    private int reconnections = 0;
    private static final int MAX_RECONNECTIONS = 5;
    boolean startFlag = false;

    public void connectToServer(String[] args) {

        Map env = System.getenv();
        String portname = (String) env.get(PORT_ENV_VAR);
        CollectionLoader.load(collection, args);
        if (portname == null) {
            Printer.printPortIsMissing();
        } else {
            port = Integer.parseInt((String) env.get(PORT_ENV_VAR));
            Printer.printClientStart();
            connect();
        }
    }
    public void startWorking(){
        while (true){
            writeMessageToServer();
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
        response.printMessage();

        } catch (ClassNotFoundException e){
        Printer.printClassNotFound();
        } catch (IOException e){
            Printer.printClientReadError();
        }
    }
    public void writeMessageToServer() {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            if (!tryingToReconnect) box = application.executeOneCommand();

            while (box == null){
                box = application.executeOneCommand();
            }

            oos.writeObject(box);
            client.write(ByteBuffer.wrap(baos.toByteArray()));

        } catch (IOException e) {
            Printer.printClientWriteError();
            connection = false;
            tryToReconnect();
            if (connection){
                writeMessageToServer();
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
