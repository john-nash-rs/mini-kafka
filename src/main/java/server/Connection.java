package server;

import server.commands.Command;
import server.commands.CommandFactory;
import server.commands.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
            System.out.println("Message recieved :: "+resp);
            Context context = new Context(resp);
            Command command = CommandFactory.get(context);
            command.execute(context);
            printStream.println(context.getCommand()+" "+resp+" "+context.getArgs());

            printStream.close();
            this.socket.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to read data from the socket",e);
        }
    }
}
