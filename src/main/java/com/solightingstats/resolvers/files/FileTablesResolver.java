package com.solightingstats.resolvers.files;

import com.solightingstats.model.ExportTable;
import java.util.List;
import java.util.stream.Collectors;

public class FileTablesResolver {
    public static List<ExportTable> getTables(List<String> tables) {
        try {
            return tables.stream()
                         .filter(table -> table != null && !table.isEmpty())
                         .map(table -> new ExportTable(table))
                         .collect(Collectors.toList());
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
