package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 双向链表结构
 * @date 2024/1/8 19:31
 */
public class CommonDoubleList<T> {
    
    public T value;
    
    public CommonDoubleList<T> pre;
    
    public CommonDoubleList<T> next;
    
    public CommonDoubleList() {
    }
    
    public CommonDoubleList(T t) {
        this.value = t;
    }
    
    @Override
    public String toString() {
        return "CommonDoubleList{" +
                "value=" + value +
                '}';
    }
}
