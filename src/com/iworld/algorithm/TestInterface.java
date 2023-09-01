package com.iworld.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/2/7 15:12
 */
public class TestInterface {
    private static final ConcurrentMap<Class<?>, ThreadPoolExecutor> EXECUTOR_MAP = new ConcurrentHashMap<Class<?>, ThreadPoolExecutor>();
    private static final int CPU_NUMBER = Runtime.getRuntime().availableProcessors();
    
    private static ExecutorService executorService = TestInterface
            .getThreadPoolExecutor(TestInterface.class);
    
    public static ThreadPoolExecutor getThreadPoolExecutor(Class<?> clazz) {
        ThreadPoolExecutor poolExecutor = EXECUTOR_MAP.get(clazz);
        if (poolExecutor == null) {
            poolExecutor = new ThreadPoolExecutor(CPU_NUMBER * 2, CPU_NUMBER * 4, 0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());
            ThreadPoolExecutor temp = EXECUTOR_MAP.putIfAbsent(clazz, poolExecutor);
            if (temp != null) {
                poolExecutor = temp;
            }
        }
        return poolExecutor;
    }
    
    public static void main(String[] args) {
        List<TestAbsClass> p = new ArrayList<>();
        p.add(new TestAbsClassSubA());
        p.add(new TestAbsClassSubB());
        CountDownLatch countDownLatch = new CountDownLatch(p.size());
        for (TestAbsClass testAbsClass : p) {
            executorService.execute(() -> {
                try {
                    testAbsClass.init();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (TestAbsClass testAbsClass : p) {
            executorService.execute(() -> {
                testAbsClass.execute();
            });
        }
        Set<Map.Entry<Class<?>, ThreadPoolExecutor>> entries = EXECUTOR_MAP.entrySet();
        for(Map.Entry<Class<?>, ThreadPoolExecutor> entry : entries) {
            System.out.println("EXECUTOR_MAP==" + entry.getKey() + "===" + entry.getValue());
        }
    }
}
abstract class TestAbsClass{
    private static ExecutorService executorService = TestInterface
            .getThreadPoolExecutor(TestAbsClass.class);
    
    private boolean isInit = false;
    
    private CountDownLatch countDownLatch;
    
    private static List<ParentWork> works = new ArrayList<>();
    
    public TestAbsClass(){
        super();
        System.out.println("TestAbsClass new" + this + "==" + works.size());
    }
    
    public void init(){
        if(!isInit){
            System.out.println(this.getClass() + "init");
            isInit = true;
            this.initWork();
            if(works != null){
                countDownLatch = new CountDownLatch(works.size());
                for (ParentWork work : works){
                    executorService.execute(work);
                }
            }
        }
    }
    
    public void execute(){
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executeResult();
    }
    
    public abstract void executeResult();
    
    
    
    /**
     * 初始化
     */
    public abstract void initWork();
    
    public abstract class ParentWork implements Runnable{
    
        public ParentWork(){
            super();
            works.add(this);
        }
        @Override
        public void run() {
            try {
                doWork();
            } finally {
                countDownLatch.countDown();
            }
        }
    
        /**
         * 初始化参数
         */
        public abstract void doWork();
    }
    
    
}
class TestAbsClassSubA extends TestAbsClass{
    
    @Override
    public void executeResult() {
        System.out.println("TestAbsClassSubA execute");
    }
    
    @Override
    public void initWork() {
        new ParentWork(){
            
            @Override
            public void doWork() {
                System.out.println("TestAbsClassSubA init");
            }
        };
    }
}
class TestAbsClassSubB extends TestAbsClass{
    
    @Override
    public void executeResult() {
        System.out.println("TestAbsClassSubB execute");
    }
    
    @Override
    public void initWork() {
        new ParentWork(){
            
            @Override
            public void doWork() {
                System.out.println("TestAbsClassSubB init");
            }
        };
    }
}

