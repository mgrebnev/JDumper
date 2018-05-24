package com.solightingstats.handler.sql.impl;

import com.solightingstats.handler.sql.QueryInitializer;
import com.solightingstats.handler.sql.model.EntityColumn;
import com.solightingstats.handler.sql.model.EntityInfo;
import com.solightingstats.handler.sql.model.enums.ColumnType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleQueryInitializer implements QueryInitializer {
    private String tableNameWrapper;
    private String stringValuesWrapper;
    
    {
       this.tableNameWrapper = "\'"; 
       this.stringValuesWrapper = "\'";
    }
    
    @Override
    public String getInsertQuery(EntityInfo entityInfo, List<String> data) {
        String tableName = entityInfo.getTableName();
        List<EntityColumn> tableColumns = entityInfo.getColumns();
        
        return getFirstPartQuery(tableName,tableColumns) + getSecondPart(tableColumns,data);
    }

    @Override
    public String getInsertQuery(String schema, EntityInfo entityInfo, List<String> data) {
        String tableName = schema + "." + entityInfo.getTableName();
        List<EntityColumn> tableColumns = entityInfo.getColumns();

        return getFirstPartQuery(tableName,tableColumns) + getSecondPart(tableColumns,data);
    }



    private String getFirstPartQuery(String table, List<EntityColumn> columns){
        return "INSERT INTO " 
                + table + " (" 
                + columns.stream()
                         .map(column -> tableNameWrapper + column.getName() + tableNameWrapper)
                         .collect(Collectors.joining(","))
                + ") ";
    }

    private String getSecondPart(List<EntityColumn> columns, List<String> data){
        String resultPartOfQuery = "VALUES (";
        for (int i = 0; i < data.size(); i++){
            String currentValue = data.get(i);
            EntityColumn currentColumn = columns.get(i);
            
            switch (currentColumn.getType()){
                case STRING:{
                    resultPartOfQuery += stringValuesWrapper + currentValue + stringValuesWrapper;
                    break;
                }
                case BOOLEAN:{
                    resultPartOfQuery += currentValue;
                    break;
                }
                case INTEGER:{
                    resultPartOfQuery += currentValue;
                }
            }
            
            //lol
            resultPartOfQuery += i != data.size() - 1 ? ", " : "";
        }
        resultPartOfQuery += ")";
        
        return resultPartOfQuery;
    }
    
    @Override
    public void setTableNameWrapper(String wrapper) {
        this.tableNameWrapper = wrapper;
    }

    @Override
    public void setStringValuesWrapper(String wrapper) {
        this.stringValuesWrapper = wrapper;
    }
}
