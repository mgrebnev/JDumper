package com.solightingstats.handler.app.impl;

import com.solightingstats.handler.app.OperationHandler;
import com.solightingstats.handler.app.enums.Operation;
import com.solightingstats.handler.app.enums.OperationResult;

import java.sql.Connection;

public class TableListInitializationHandler implements OperationHandler{
    @Override
    public OperationResult execute(Connection connection) {
        return null;
    }

    @Override
    public Operation getOperation() {
        return Operation.INIT_TABLE_LIST;
    }
}
