package com.solightingstats;

import com.solightingstats.environments.DatabaseEnvironments;
import com.solightingstats.environments.resolvers.DatabaseEnvironmentsResolver;
import com.solightingstats.handler.app.OperationHandler;
import com.solightingstats.handler.app.enums.Operation;
import com.solightingstats.handler.app.enums.OperationResult;
import com.solightingstats.handler.app.impl.TableListInitializationHandler;
import com.solightingstats.handler.jdbc.JdbcConnectionFactory;
import com.solightingstats.resolvers.console.ApplicationArgumentsResolver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Map<Operation,OperationHandler> defaultApplicationHandlers = new HashMap<>();
    
    static {
        OperationHandler tableInitializerHandler = new TableListInitializationHandler();
        
        defaultApplicationHandlers.put(tableInitializerHandler.getOperation(), tableInitializerHandler);
    }
    
    public static void main(String[] args) throws Exception {
        args = new String[]{"-i"};
        try {
            final DatabaseEnvironments environments = DatabaseEnvironmentsResolver.getEnvironments();
            final Connection connection = JdbcConnectionFactory.getConnection(environments);

            List<Operation> currentOperations = ApplicationArgumentsResolver.resolve(args);
            currentOperations.forEach(operation -> {
                OperationHandler currentOperationHandler = defaultApplicationHandlers.get(operation);
                OperationResult operationResult = currentOperationHandler.execute(environments,connection);
                
                System.out.println(operationResult);
            });
            
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
    }
}
