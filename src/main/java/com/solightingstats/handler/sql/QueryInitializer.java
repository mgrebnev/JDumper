package com.solightingstats.handler.sql;

import com.solightingstats.handler.sql.model.EntityInfo;

import java.util.List;

public interface QueryInitializer {
    String getInsertQuery(EntityInfo entityInfo, List<String> data);
    String getInsertQuery(String schema, EntityInfo entityInfo, List<String> data);
    void setTableNameWrapper(String wrapper);
    void setStringValuesWrapper(String wrapper);
}
