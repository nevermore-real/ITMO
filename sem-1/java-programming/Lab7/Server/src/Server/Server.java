package Server;

import Commands.CommandBox;
import MajorClasses.Printer;
import MajorClasses.Product;
import MajorClasses.Response;
import db.DatabaseHandler;
import java.security.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Base64;

public class Server {

    //private static final Logger log = LogManager.getLogger();

    Selector selector;
    SelectionKey key;
    ServerSocketChannel serverSocketChannel;
    ServerRequest serverRequest;
    Response response;
    CommandBox commandBox;
    DatabaseHandler databaseHandler;
    ArrayDeque<Product> collection;
    private final int port = 11111;
    private final String host = "localhost";

    public int start() {
        try {
            Printer.printServerStart();
            databaseHandler = new DatabaseHandler();
            if (databaseHandler.connectToDataBase() == 1) {
                collection = databaseHandler.loadCollection();
                serverRequest = new ServerRequest(collection);

                selector = Selector.open();
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.socket().bind(new InetSocketAddress(host, port));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                return 1;
            }else return 0;

        }catch (IOException e){
            Printer.printServerStartFailed();
            return 0;
        }
    }

    public void read() throws IOException {

        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        try {
            socketChannel.read(buffer);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
            ObjectInputStream input = new ObjectInputStream(bais);
            commandBox = (CommandBox) input.readObject();
            if (commandBox.getCommand().equals("auth")) {
                response = new Response();
                String login = commandBox.getArrayArgs().get(0);
                String password = commandBox.getArrayArgs().get(1);
                password = hashPassword(password);
                databaseHandler.setUserLogin(login);
                databaseHandler.setUserPassword(password);

                int id = databaseHandler.getUserIdByLogin(login, password);
                if (id == 0) {
                    response.setMessage("Пользователь не найден. Создать нового с такими данными?  -- yes / no");
                    response.setFlagTrue();
                } else {
                    databaseHandler.setUserId(id);
                    response.setMessage("Авторизация прошла успешно!");
                    response.setCollection(collection);
                }
            }else if (commandBox.getCommand().equals("createNewUser")){
                int id = databaseHandler.registerNewUser();

                databaseHandler.setUserId(id);
                response.setMessage("Успешная регистрация нового пользователя!");
                response.setCollection(collection);

            }else{response = serverRequest.doRequest(commandBox, databaseHandler);
                response.setCollection(databaseHandler.loadCollection());
            }
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        } catch (IOException e) {
            Printer.printServerReadException();
            key.cancel();
        } catch (ClassNotFoundException e) {
            Printer.printClassNotFound();
        }
    }
    public void sendResponse(){

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream output = new ObjectOutputStream(byteArrayOutputStream)) {
            output.writeObject(response);
            SocketChannel sc = (SocketChannel) key.channel();
            sc.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
            sc.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            Printer.printServerWriteException();
        }
    }
    public void  setKey(SelectionKey key){
        this.key = key;
    }
    public Selector getSelector() {
        return this.selector;
    }
    public void register() {
        try {
            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            Printer.printRegistered();
        }
        catch (IOException e) {
            Printer.printServerRegisterException();
        }
    }
    public String hashPassword(String password){

        try {
            byte[] bytesOfMessage = password.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
             return Base64.getEncoder().encodeToString(md.digest(bytesOfMessage));
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException e){
            System.out.println("Ошибка при кодировании пароля");
            return null;
        }
    }
}