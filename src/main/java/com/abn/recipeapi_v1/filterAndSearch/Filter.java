package com.abn.recipeapi_v1;

public class Filter {
    private String key;
    private String operation;
    private Object value;

    public Filter(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getOperation() {
        return operation;
    }

    public Object getValue() {
        return value;
    }
}
