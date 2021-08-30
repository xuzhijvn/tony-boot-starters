/*
 *       Copyright© (2020).
 */
package com.tony.data.permission;

/**
 * @author tony
 * @create 2021-08-25
 * @description:
 */
public class Permission {
    private String tableName;
    private String tableField;
    private String fieldValue;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableField() {
        return tableField;
    }

    public void setTableField(String tableField) {
        this.tableField = tableField;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
