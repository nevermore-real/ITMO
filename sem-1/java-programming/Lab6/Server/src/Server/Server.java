package Server;

import Commands.CommandBox;
import MajorClasses.Printer;
import MajorClasses.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    //private static final Logger log = LogManager.getLogger();

    Selector selector;
    SelectionKey key;
    ServerSocketChannel serverSocketChannel;
    ServerRequest serverRequest = new ServerRequest();
    Response response;
    CommandBox commandBox;
    private final int port = 11111;
    private final String host = "localhost";

    public void start() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(host, port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            Printer.printServerStart();
        }catch (IOException e){
            Printer.printServerStartFailed();
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
            response = serverRequest.doRequest(commandBox);
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        } catch (IOException e) {
            Printer.printServerReadException();
            key.cancel();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
}