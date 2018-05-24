package com.solightingstats.resolvers.json;

import com.solightingstats.factory.ObjectMapperFactory;
import com.solightingstats.model.ExportTable;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonTablesFileResolver {
    public static List<ExportTable> getTables(String fileData) throws IOException {
        try {
            ObjectMapper mapper = ObjectMapperFactory.getDefaultObjectMapper();
            return mapper.readValue(fileData,List.class);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
