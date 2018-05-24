package com.solightingstats.handler.sql.model;

import com.solightingstats.handler.sql.model.enums.ColumnType;
import lombok.Data;

@Data
public class EntityColumn {
    private String name;
    private ColumnType type;
}
