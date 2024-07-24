import MajorClasses.Printer;
import Server.Server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        if (server.start()== 1) {
            Selector selector = server.getSelector();
            while (true) {
                try {
                    selector.select(2000);
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> i = selectedKeys.iterator();

                    while (i.hasNext()) {

                        SelectionKey key = i.next();
                        server.setKey(key);
                        i.remove();

                        if (key.isAcceptable()) {
                            server.register();

                        } else if (key.isReadable()) {
                            server.read();

                        } else if (key.isWritable()) {
                            server.sendResponse();
                        }
                    }
                } catch (IOException e) {
                    Printer.printIOException();
                }
            }
        }
    }
}
