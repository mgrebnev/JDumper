package com.solightingstats;

import com.solightingstats.handler.sql.QueryInitializer;
import com.solightingstats.handler.sql.impl.SimpleQueryInitializer;
import com.solightingstats.handler.sql.model.EntityColumn;
import com.solightingstats.handler.sql.model.EntityInfo;
import com.solightingstats.handler.sql.model.enums.ColumnType;
import com.solightingstats.model.ExportTable;
import com.solightingstats.resolvers.json.JsonTablesFileResolver;
import com.solightingstats.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
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

        ClassLoader classLoader = Main.class.getClassLoader();
        
        File jsonTableFile = new File(classLoader.getResource("json/tables.json").getFile());
        String text = FileUtil.getText(jsonTableFile);
        List<ExportTable> tables = JsonTablesFileResolver.getTables(text);
        for (ExportTable table: tables)
            System.out.println(table.getId() + " " + table.getName());
    }
}
