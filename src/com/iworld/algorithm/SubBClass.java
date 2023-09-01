package com.iworld.algorithm;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/4/27 16:46
 */
public class SubBClass extends ParentClass {
    
    protected String name = "1";
    
    @Override
    public void initWorks() {
        new MyWork() {
            
            @Override
            public void doWork() {
            
          }
        };
    }
    
    public static void main(String[] args) {
        int i = 0;
        do {
            System.out.println(i);
            i++;
        } while (i < 100);
        System.out.println(i);
    }
}
