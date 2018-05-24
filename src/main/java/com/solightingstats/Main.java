package com.solightingstats;

import com.solightingstats.handler.sql.QueryInitializer;
import com.solightingstats.handler.sql.impl.SimpleQueryInitializer;
import com.solightingstats.handler.sql.model.EntityColumn;
import com.solightingstats.handler.sql.model.EntityInfo;
import com.solightingstats.handler.sql.model.enums.ColumnType;
import com.solightingstats.resolvers.json.JsonTablesFileResolver;
import com.solightingstats.utils.FileUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        QueryInitializer queryInitializer = new SimpleQueryInitializer();
        
        queryInitializer.setStringValuesWrapper("'");
        queryInitializer.setTableNameWrapper("");

        EntityColumn firstColumn = new EntityColumn();
        firstColumn.setName("ID");
        firstColumn.setType(ColumnType.INTEGER);
        
        EntityColumn secondColumn = new EntityColumn();
        secondColumn.setName("TASK_DESCRIPTION");
        secondColumn.setType(ColumnType.STRING);        
        
        EntityColumn thirdColumn = new EntityColumn();
        thirdColumn.setName("STATUS");
        thirdColumn.setType(ColumnType.BOOLEAN);

        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setTableName("PEOPLE");
        entityInfo.setColumns(Arrays.asList(firstColumn,secondColumn,thirdColumn));
        
        
        List<String> rowData = Arrays.asList("1","DVER SDELAL","FALSE");
        
        String resultQuery = queryInitializer.getInsertQuery(entityInfo,rowData);

        File jsonTableFile = new File("/home/max/db/test.json");
        String text = FileUtil.getText(jsonTableFile);
        JsonTablesFileResolver.
    }
}
