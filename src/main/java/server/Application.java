package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {
    private ServerSocket serverSocket;
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
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
