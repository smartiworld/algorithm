package com.iworld.algorithm.insurance;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2025/6/30 16:33
 */
public enum TrueOrFalse {
    
    /**
     * 是
     */
    TRUE(1,"是"),
    /**
     * 否
     */
    FALSE(0,"否");
    
    /**
     * 枚举值
     */
    private Integer value;
    
    /**
     * 枚举说明
     */
    private String desc;
    
    private TrueOrFalse(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    
    public Integer getValue() {
        return value;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public static Integer getValByName(String name) {
        for(TrueOrFalse e : TrueOrFalse.values()){
            if (e.getDesc().equals(name)){
                return e.getValue();
            }
        }
        return null;
    }
    public static String getDescByValue(Integer val){
        if (val == null){
            return null;
        }
        for(TrueOrFalse e : TrueOrFalse.values()){
            if (e.getValue().equals(val)){
                return e.getDesc();
            }
        }
        return null;
    }
}
