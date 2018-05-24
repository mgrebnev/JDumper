package com.solightingstats.resolvers.json;

import com.solightingstats.factory.ObjectMapperFactory;
import com.solightingstats.model.ExportTable;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

public class JsonTablesFileResolver {
    public static List<ExportTable> getTables(String fileData) {
        try {
            ObjectMapper mapper = ObjectMapperFactory.getDefaultObjectMapper();

            TypeReference reference = new TypeReference<List<ExportTable>>() {};
            return mapper.readValue(fileData,reference);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
