package com.solightingstats.handler.app;

import com.solightingstats.environments.DatabaseEnvironments;
import com.solightingstats.handler.app.enums.Operation;
import com.solightingstats.handler.app.enums.OperationResult;

import java.sql.Connection;

public interface OperationHandler {
    OperationResult execute(DatabaseEnvironments environments, Connection connection);
    Operation getOperation();
}
