package com.iworld.algorithm.insurance;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2025/3/16 15:01
 */
public interface EnumValue<T> {
    
    /**
     * Enum中定义的名字
     * @return
     */
    String getName();
    
    
    /**
     * Enum中定义的索引
     * @return
     */
    int getIndex();
    
}
