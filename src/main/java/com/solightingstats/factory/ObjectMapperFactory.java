package com.solightingstats.factory;

import org.codehaus.jackson.map.ObjectMapper;

public class ObjectMapperFactory {
    public static ObjectMapper getDefaultObjectMapper(){
        return new ObjectMapper();
    }
}
