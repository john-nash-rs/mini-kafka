package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Runnable {
    private Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        try {
            PrintStream printStream = new PrintStream(this.socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String resp = in.readLine();
            Thread.sleep(5000);
            System.out.println("Message recieved :: "+resp);
            for (int i = 100; i >= 0; i--) {
                printStream.println(i + " bottles of beer on the wall "+resp);
            }
            printStream.close();
            this.socket.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to read data from the socket",e);
        }
    }
}
