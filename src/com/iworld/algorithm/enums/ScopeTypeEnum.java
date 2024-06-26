package com.iworld.algorithm.enums;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/6/25 10:41
 */
public enum ScopeTypeEnum {
    /**
     *
     */
    CONVENTION_SCOPE(1, "常规时段"),
    SPECIAL_SCOPE(2, "特殊时段");
    
    /**
     * index
     */
    Integer index;
    /**
     * 描述
     */
    String desc;
    
    ScopeTypeEnum(Integer index, String desc) {
        this.index = index;
        this.desc = desc;
    }
    /**
     * 根据index获取实例
     * @param index
     * @return
     */
    public static ScopeTypeEnum valueOfIndex(Integer index) {
        if (index == null) {
            return null;
        }
        for (ScopeTypeEnum codeEnum : values()) {
            if (codeEnum.getIndex().equals(index)) {
                return codeEnum;
            }
        }
        return null;
    }
    
    public static String getDescByIndex(Integer index) {
        if(index ==null) {
            return null;
        }
        for (ScopeTypeEnum codeEnum : values()) {
            if (codeEnum.getIndex().equals(index)) {
                return codeEnum.getDesc();
            }
        }
        return null;
    }
    
    public Integer getIndex() {
        return index;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
