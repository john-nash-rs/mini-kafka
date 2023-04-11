package server.commands;

public class CreateTopic implements Command{

    public void execute(Context context) {
        System.out.println(" Hello Topic  "+context.getCommand());
    }
}
