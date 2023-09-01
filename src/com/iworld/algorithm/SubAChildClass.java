package com.iworld.algorithm;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2022/4/1 14:41
 */
public class SubAChildClass extends SubAClass {
    
    public static void main(String[] args) {
        SubAChildClass subAClass = new SubAChildClass();
        SubAClass subAClass1 = new SubAClass();
        System.out.println(subAClass.getClass().isAssignableFrom(subAClass1.getClass()));
        System.out.println(subAClass1.getClass().isAssignableFrom(subAClass.getClass()));
    }
    
    public ParentClass getCusClass() {
        return new SubAChildClass();
    }
}
