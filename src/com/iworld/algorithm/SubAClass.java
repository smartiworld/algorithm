package com.iworld.algorithm;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/4/27 16:46
 */
public class SubAClass extends AbstractAClass {
    
    public final void a () {
        super.outName();
    }
    
    @Override
    public void b() {
        System.out.println("b SubAClass");
    }
    
}
