package com.iworld.algorithm;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2022/4/1 14:37
 */
public class AbstractAClass extends ParentClass {
    
    @Override
    public void initWorks() {
        b();
    }
    
    public void b() {
        System.out.println("b AbstractAClass");
    }
}
