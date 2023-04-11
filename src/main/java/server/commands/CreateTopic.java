package server.commands;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateTopic implements Command{

    private static final Logger logger = LogManager.getLogger(CreateTopic.class);
    public void execute(Context context) {
        logger.info(" Hello Topic  "+context.getCommand());
    }
}
