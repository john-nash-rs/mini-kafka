package server.commands;

public class CommandFactory {


    public static Command get(Context context) {

        System.out.println(Constants.Commands.valueOf(context.getCommand()));
        switch (Constants.Commands.valueOf(context.getCommand())){
            case CREATE_TOPIC:
                return new CreateTopic();
        }
        return null;
    }
}
