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
public class Context {
    private String command;
    private String value;
    private Map<String, String> args;

    public Context(String expression){
        args= new HashMap<>();
        char[] arr = expression.toCharArray();

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
        String[] argAndValue = builder.toString().split("=");
        if(isOp){
            command = argAndValue[0].toUpperCase();
            value = argAndValue[1].toUpperCase();
            isOp = false;
        } else {
            args.put(argAndValue[0].toUpperCase(), argAndValue[1].toUpperCase());
        }
        builder.setLength(0);
        //noinspection ConstantValue
        return isOp;
    }
}
