package com.iworld.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/4/27 16:45
 */
public abstract class ParentClass {
    
    private List<MyWork> p = new ArrayList<>();
    protected String name;
    
    public void outName() {
        System.out.println(name);
    }
    private boolean isInit = false;
    public void initData() {
        if(!isInit) {
            this.initWorks();
            for (MyWork myWork : p) {
                System.out.println(myWork);
            }
            isInit = true;
        }
    }
    public abstract void initWorks();
    
    protected abstract class MyWork {
        protected MyWork() {
            p.add(this);
        }
        
        public abstract void doWork();
    }
    
    public static void main(String[] args) {
        int a = -1;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(a >>> (Integer.MAX_VALUE - 3));
    }
}
