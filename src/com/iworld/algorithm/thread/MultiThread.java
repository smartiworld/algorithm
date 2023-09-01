package com.iworld.algorithm.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/12/17 14:02
 */
public class MultiThread {
    
    public static void main(String[] args) {
        //ExecutorService exe = new ThreadPoolExecutor(1, 3, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));
        for (int i = 0; i < 10; i++) {
//            Thread t1 = new Thread(new WorkTask("A", null));
//            Thread t2 = new Thread(new WorkTask("B", t1));
//            Thread t3 = new Thread(new WorkTask("C", t2));
//            t1.start();
//            t2.start();
//            t3.start();
//            try {
//                t3.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            //exe.execute(new WorkTask());
        }
        int a = 3;
        System.out.println(a+=a*=a);
    }
    
    public void printThread() {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new WorkTask("A", null));
            Thread t2 = new Thread(new WorkTask("B", t1));
            Thread t3 = new Thread(new WorkTask("C", t2));
            t1.start();
            t2.start();
            t3.start();

        }
    }
    
    static class WorkTask implements Runnable {
        
        Thread thread;
        
        String text;
        WorkTask(String text, Thread thread) {
            this.thread = thread;
            this.text = text;
        }
        @Override
        public void run() {
            if (thread != null) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(text);
        }
    }
    
}
