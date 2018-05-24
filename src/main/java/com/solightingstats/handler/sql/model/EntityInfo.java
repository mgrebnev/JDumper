package com.solightingstats.handler.sql.model;

import lombok.Data;

import java.util.List;

@Data
public class EntityInfo {
    private String tableName;
    private List<EntityColumn> columns;
}
