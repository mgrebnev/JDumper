package com.solightingstats.resolvers.console;

import com.solightingstats.handler.app.enums.Operation;

import java.util.*;
import java.util.stream.Collectors;

public class ApplicationArgumentsResolver {
    public final static Map<String,Operation> operationArgs = new HashMap<>();
    
    static {
        operationArgs.put("-i", Operation.INIT_TABLE_LIST);
        operationArgs.put("-sql",Operation.SQL_DUMP);
        operationArgs.put("-xml",Operation.XML_DUMP);
    }
    
    public static List<Operation> resolve(String[] arguments){
        return Arrays.stream(arguments)
                     .map(operationArgs::get)
                     .filter(Objects::nonNull)
                     .collect(Collectors.toList());
    }
}
