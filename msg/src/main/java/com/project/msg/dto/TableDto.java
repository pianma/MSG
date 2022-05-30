package com.project.msg.dto;

import lombok.Data;

@Data
public class TableDto {

    private String tableName;

    private String field;

    private String type;
    private String javaType;

    private String isNullable;
    private String key;
    private String extra;
}
