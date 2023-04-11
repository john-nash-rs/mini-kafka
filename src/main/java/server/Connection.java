package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.commands.Command;
import server.commands.CommandFactory;
import server.commands.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Connection implements Runnable {

    private static final Logger logger = LogManager.getLogger(Connection.class);
    private final Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        try {
            PrintStream printStream = new PrintStream(this.socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String resp = in.readLine();
            logger.info("Message received :: "+resp);
            Context context = new Context(resp);
            Command command = CommandFactory.get(context);
            assert command != null;
            command.execute(context);
            printStream.println(context.getCommand()+" "+resp+" "+context.getArgs());

            printStream.close();
            this.socket.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to read data from the socket",e);
        }
    }
}
