package server.commands;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateTopic implements Command{

    private static final Logger logger = LogManager.getLogger(CreateTopic.class);
    public void execute(Context context) {
        logger.info(" Hello Topic  "+context.getCommand());
        String topicName = context.getValue();
        int partitionCount = context.getArgs().get(Constants.PARTITION) == null ? Constants.DEFAULT_COUNT_ONE : Integer.parseInt(context.getArgs().get(Constants.PARTITION));
        int replicaCount = context.getArgs().get(Constants.REPLICA) == null ? Constants.DEFAULT_COUNT_ONE : Integer.parseInt(context.getArgs().get(Constants.REPLICA));

        String prefix = Constants.FILE_DIRECTORY +
                "/" +
                topicName +
                "_";
        StringBuilder fileNameBuffer = new StringBuilder();
        for(int i = 0; i < partitionCount; i++){
            for(int j = 0; j < replicaCount; j++){
                String fileName = fileNameBuffer.append(prefix).append(i).append("_").append(j).toString();
                try {
                    Files.createFile(Paths.get(fileName));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                fileNameBuffer.setLength(0);
            }
        }

    }
}
