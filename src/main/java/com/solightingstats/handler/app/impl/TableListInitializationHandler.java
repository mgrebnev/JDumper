package com.solightingstats.handler.app.impl;

import com.solightingstats.environments.DatabaseEnvironments;
import com.solightingstats.handler.app.OperationHandler;
import com.solightingstats.handler.app.enums.Operation;
import com.solightingstats.handler.app.enums.OperationResult;
import com.solightingstats.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TableListInitializationHandler implements OperationHandler{
    private static final Integer DEFAULT_SCHEMA_INDEX = 1;
    private static final Integer DEFAULT_TABLE_INDEX = 3;

    @Override
    public OperationResult execute(DatabaseEnvironments environments, Connection connection) {
        try {
            String schema = environments.getSchema();
            
            DatabaseMetaData currentMetadata = connection.getMetaData();
            ResultSet schemas = currentMetadata.getSchemas();
            
            List<String> schemaTables = new ArrayList<>();
            while (schemas.next()){
                String currentSchema = schemas.getString(DEFAULT_SCHEMA_INDEX);
                if (currentSchema.equals(schema)){
                    ResultSet tablesSet = currentMetadata.getTables(currentSchema,currentSchema,"%",null);
                    while (tablesSet.next()){
                        String currentTable = tablesSet.getString(DEFAULT_TABLE_INDEX);
                        schemaTables.add(currentTable);
                    }
                }
            }
            
            saveTablesToDefaultFile(schemaTables);
            
            return OperationResult.OK;
        }catch (Exception ex){
            // to-do check ex
            return OperationResult.UNKNOWN_ERROR;
        }
    }

    private void saveTablesToDefaultFile(List<String> tables) throws IOException {
        if (tables.isEmpty())
            return;
        else {
            Path tablesFilePath = new File(FileUtil.getCurrentPath() + "/tables.data").toPath();
            Files.write(tablesFilePath,tables, 
                        Charset.defaultCharset(), 
                        StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING
            );
        }
    }
    
    @Override
    public Operation getOperation() {
        return Operation.INIT_TABLE_LIST;
    }
}
