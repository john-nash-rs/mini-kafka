package server.commands;

public class CommandFactory {


    public static Command get(Context context) {

        System.out.println(Constants.Commands.valueOf(context.getCommand()));
        if (Constants.Commands.valueOf(context.getCommand()) == Constants.Commands.CREATE_TOPIC) {
            return new CreateTopic();
        }
        return null;
    }
}
