package com.solightingstats.resolvers.console;

import com.solightingstats.handler.app.enums.Operation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationArgumentsResolver {
    public final static Map<String,Operation> operationArgs = new HashMap<>();
    
    static {
        System.out.println("Init");
        operationArgs.put("-i", Operation.INIT_TABLE_LIST);
        operationArgs.put("-sql",Operation.SQL_DUMP);
        operationArgs.put("-xml",Operation.XML_DUMP);
    }
    
    public static List<Operation> resolve(String[] arguments){
        return Arrays.stream(arguments)
                     .map(argument -> operationArgs.getOrDefault(argument,Operation.INIT_TABLE_LIST))
                     .collect(Collectors.toList());
    }
}
