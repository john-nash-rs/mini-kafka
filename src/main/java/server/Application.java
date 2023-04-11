package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.commands.CreateTopic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);
    private ServerSocket serverSocket;
    public static void main(String[] args) throws Exception {
        logger.info("Hello World");
        Application application = new Application();
        application.startTcpServer();

    }

    public void startTcpServer() {
        try {
            this.serverSocket = new ServerSocket(8000);
            while(true){
               Socket socket = this.serverSocket.accept();
               Thread th = new Thread(new Connection(socket));
               th.start();
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to start TCP server :( ",e);
        }

    }


}
