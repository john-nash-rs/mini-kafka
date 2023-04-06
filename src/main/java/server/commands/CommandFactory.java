package server.commands;

public class CommandFactory {


    public static Command get(Context context) {

        switch (Constants.Commands.valueOf(context.getCommand())){
            case CREATE_TOPIC:
                System.out.println("Give me create topic factory");
                break;
        }
        return null;
    }
}
