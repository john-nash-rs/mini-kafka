package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.commands.Constants;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        logger.info("Hello World");
        Runtime.getRuntime().addShutdownHook(
                new Thread(Application::shutdownInitiated
                ));
        Files.createDirectories(Paths.get(Constants.FILE_DIRECTORY));
        Application application = new Application();
        application.startTcpServer();


    }

    private static void shutdownInitiated() {
        logger.info("--Shutdown hook working --");
        try {
            File directory = new File(Constants.FILE_DIRECTORY);
            File[] files = directory.listFiles();
            assert files != null;
            for(File file : files) {
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }
            Files.deleteIfExists(Paths.get(Constants.FILE_DIRECTORY));
        } catch (IOException e) {
            throw new RuntimeException("Could not delete "+Constants.FILE_DIRECTORY);
        }
    }

    public void startTcpServer() {
        try {
            @SuppressWarnings("resource") ServerSocket serverSocket = new ServerSocket(8000);
            //noinspection InfiniteLoopStatement
            while(true){
               Socket socket = serverSocket.accept();
               Thread th = new Thread(new Connection(socket));
               th.start();
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to start TCP server :( ",e);
        }

    }


}
