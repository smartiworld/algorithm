package com.iworld.algorithm.enums;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/6/25 10:40
 */
public enum DataTypeEnum {
    
    /**
     *
     */
    MODEL(1, "车型"),
    MODEL_GROUP(2, "车型组"),
    DEPT(3, "门店"),
    ;
    
    private Integer index;
    
    private String name;
    
    DataTypeEnum(Integer index, String name) {
        this.index = index;
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Integer getIndex() {
        return this.index;
    }
}
