package server.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/**
 * create topic --partition=2 --replica=2
 */
public class Context {
    private String command;
    private Map<String, String> args;

    public Context(String expression){
        args= new HashMap<String, String>();
        char[] arr = expression.toCharArray();

        //List<Character> param = new ArrayList<Character>();
        StringBuilder builder = new StringBuilder();

        boolean isOp = true;
        int len = arr.length;
        for(int i = 0; i < len; i++){
            if((i+1  < len) && (arr[i] == arr[i+1]) && arr[i] == '-'){
                isOp = setCommandContext(builder, isOp);
            }
            if(arr[i] != '-' && arr[i] != ' ')
                builder.append(arr[i]);
        }
        setCommandContext(builder, isOp);
    }

    private boolean setCommandContext(StringBuilder builder, boolean isOp) {
        if(isOp){
            command = builder.toString().toUpperCase();
            isOp = false;
        } else {
            String[] argAndValue = builder.toString().split("=");
            args.put(argAndValue[0].toUpperCase(), argAndValue[1].toUpperCase());
        }
        builder.setLength(0);
        return isOp;
    }
}
