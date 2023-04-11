package server.commands;

public class CommandFactory {


    public static Command get(Context context) {

        System.out.println(Constants.Commands.valueOf(context.getCommand()));
        switch (Constants.Commands.valueOf(context.getCommand())){
            case CREATE_TOPIC:
                System.out.println("Give me create topic factory");
                return new Topic();
        }
        return null;
    }
}
