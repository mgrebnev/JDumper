package com.solightingstats.environments.resolvers;

import com.solightingstats.environments.DatabaseEnvironments;
import com.solightingstats.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DatabaseEnvironmentsResolver {
    public static DatabaseEnvironments getEnvironments() throws IOException {
        File file = new File(FileUtil.getCurrentPath() + "/config.properties");
        List<String> propertyLines = FileUtil.getAllLines(file);

        return initEnvironments(propertyLines);
    }
    
    private static DatabaseEnvironments initEnvironments(List<String> propertyLines){
        DatabaseEnvironments environments = new DatabaseEnvironments();
        for (String property: propertyLines){
            String parts[] = property.split("=");

            String propertyName  = parts.length == 0 ? "" : parts[0];
            String propertyValue = parts.length == 1 ? "" : parts[1];
            
            switch (propertyName){
                case "url": 
                    { environments.setUrl(propertyValue.replaceAll(" ","")); break; }
                case "username": 
                    { environments.setUsername(propertyValue); break; }
                case "password":
                    { environments.setPassword(propertyValue); break; }
                case "driver-class-name":
                    { environments.setClassName(propertyValue.replaceAll(" ",""));}
            }
        }
        return environments;
    }
}
