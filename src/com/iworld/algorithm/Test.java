package com.iworld.algorithm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/4/27 16:33
 */
public class Test {
    private User user = new User("a");
    private static AtomicLong atom = new AtomicLong(0);
    private static Map<Long, AtomicLong> incrMap = new HashMap<>();
    private static ExecutorService exe = new ThreadPoolExecutor(0, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1000));
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
//        SubAClass a = new SubAClass();
//        SubBClass b = new SubBClass();
//        a.initData();
//        b.initData();
//        LocalDate now = LocalDate.now();
//        LocalDate preDate = now.minusDays(-1L);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        System.out.println(preDate.format(dateTimeFormatter));
//        System.out.println(preDate.plusMonths(1).format(dateTimeFormatter));
//        System.out.println(now.minusMonths(2).format(dateTimeFormatter));
//        Test t = new Test();
//        User user = t.getUser();
//        t.setValue(t.getUser(), "b");
//        System.out.println(t.getUser().getName());
//        System.out.println(user.getName());
//        long currentTime=System.currentTimeMillis()/1000;
//        System.out.println(Long.toBinaryString(currentTime));
//        System.out.println(Long.toBinaryString(currentTime<<32));
//        System.out.println(Long.toBinaryString(8192L));
//
//        long otherProjectUniqueSerialNo = getOtherProjectUniqueSerialNo(8192);
//        System.out.println(otherProjectUniqueSerialNo);
//        System.out.println(Long.toBinaryString(otherProjectUniqueSerialNo));
//        long ll = 0B11111111111111111111111L;
//        System.out.println(ll);
//        System.out.println("FZ".hashCode());
//        LocalDate of1 = LocalDate.of(2020, 2, 20);
//        LocalDate of2 = LocalDate.of(2021, 10, 10);
//        long totalMonths = Period.between(of1, of2).toTotalMonths();
//        int days = Period.between(of1, of2).getDays();
//        System.out.println(totalMonths);
//        System.out.println(days);
//        of2 = of2.with(TemporalAdjusters.firstDayOfMonth()).plusDays(27);
//        System.out.println(of2.toString());
//        System.out.println("--------------");
//        Double a = 0D;
//        BigDecimal bigDecimal = BigDecimal.valueOf(a).stripTrailingZeros();
//        System.out.println(bigDecimal.doubleValue());
//        System.out.println(bigDecimal.toPlainString());
//        System.out.println(Double.valueOf(bigDecimal.toString()));
//        int concurrentSize = 10;
//        List<Integer> list = generateIntArray(18);
//        System.out.println(list);
//        int size = (list.size() / concurrentSize) + 1;
//        Stream.iterate(0, n -> n + 1).limit(size).parallel().forEach(a -> {
//            List<Integer> subInsertOrderPriceList = list.stream()
//                    .skip(a * concurrentSize).limit(concurrentSize).parallel().collect(Collectors.toList());
//            System.out.println(subInsertOrderPriceList);
//        });
//        List<List<Integer>> collect = Stream.iterate(0, n -> n + 1)
//                .limit(size).parallel().map(a -> list.stream()
//                        .skip(a * concurrentSize).limit(concurrentSize).parallel().collect(Collectors.toList()))
//                .collect(Collectors.toList());
//        System.out.println(collect);
        //testWeek();
//        int size = 1000;
//        CountDownLatch cdl = new CountDownLatch(size);
//        List<Long> ids = new ArrayList<>(size);
//        for (int i = 0; i < size; i++) {
//            exe.execute(() -> {
//                try {
//                    long uniqueSerialNo = getOtherProjectUniqueSerialNo();
//                    if (ids.contains(uniqueSerialNo)) {
//                        System.out.println("有重复===" + uniqueSerialNo);
//                    }
//                    ids.add(uniqueSerialNo);
//                } finally {
//                    cdl.countDown();
//                }
//            });
//        }
//        try {
//            cdl.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("执行结束");
//        Test t = new Test();
//        User user = t.getUser();
//        System.out.println(user.getAge());
//        t.setUserAge(user.getAge());
//        System.out.println(user.getAge());
//        String s = " aa aa  ";
//        System.out.println(s.trim());
//        int a = 1 | 1;
//        System.out.println(a);
        Class<StringBuffer> stringBufferClass = StringBuffer.class;
        StringBuffer sb = stringBufferClass.newInstance();
        sb.append("a");
        String x = sb.toString();
        System.out.println(x);
//        Field toStringCache = stringBufferClass.getDeclaredField("toStringCache");
//        toStringCache.setAccessible(true);
//        char[] aChar = (char[])toStringCache.get(sb);
//        sb.append("b");
//        System.out.println(sb.toString());
//        System.out.println(x);
//
//        char[] a = {'1','f','l'};
//        Class<String> clazz = String.class;
//        Constructor<String> constructor = clazz.getDeclaredConstructor(char[].class, boolean.class);
//        constructor.setAccessible(true);
//        String s = constructor.newInstance(a, true);
//        System.out.println(s);
        //此时修改a[1]的值
//        a = null;
//        System.out.println(s);
        long orderId = 175386498;
        String version = "t_order_price_version_shard_";
        String price = "t_order_price_shard_";
        String record = "t_order_price_calc_record_shard_";
        System.out.println(version + (orderId % 4));
        System.out.println(record + (orderId % 4));
        System.out.println(price + (orderId % 16));
        String aa = "12345678956";
        String s1 = aa.replaceAll("\\d{4}", "****");
        System.out.println(s1);
        StringBuilder sb1 = new StringBuilder(aa);
        sb1.replace(3, 7, "****");
        System.out.println(sb1.toString());
    
    }
    public void setUserAge(Integer age) {
        if (age == null) {
            age = new Integer(12);
        }
    }
    public static List<Integer> generateIntArray(int length) {
        List<Integer> arr = new ArrayList<Integer>();
        int max = 100;
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            arr.add(r.nextInt(max));
        }
        return arr;
    }
    
    public static long getOtherProjectUniqueSerialNo()
    {
        long currentTime = System.currentTimeMillis()/1000;
        AtomicLong atomicLong = incrMap.get(currentTime);
        if (atomicLong == null) {
            atomicLong = new AtomicLong(0);
            incrMap.put(currentTime, atomicLong);
        }
        long increment = atomicLong.incrementAndGet();
        return  (Long)((currentTime<<32)|(increment));
    }
    public User getUser() {
        return this.user;
    }
    private void test() {
        Test t = new Test();
        User user = t.getUser();
        setValue(t.getUser(), "b");
        System.out.println(t.getUser().getName());
        System.out.println(user.getName());
    }
    
    private void setValue(User user, String name){
        user.setName(name);
    }
    
    class User{
        String name;
        Integer age;
    
        User() {
        
        }
        
        User(String name) {
            this.name = name;
        }
        
        User(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
        
        public Integer getAge() {
            return this.age;
        }
    }
    
    public static void testWeek() {
        // 是否是强制续租
        int week = -1;
        // 是否在租期内
        Date startDate = new Date();
        Date leaseDate;
        // 租期内第一天
        for (int i = 0; i < 35; i++) {
            // 将日期转成无时分秒的日期
            // 如果取、还车时间为空
            // isRentDay 处理
            leaseDate = DateUtil.date2DayDate(startDate);
            Date calDate = DateUtil.dateAdd2Date(leaseDate, TimeTypeEnum.DATE, i);
            //得到星期几
            if(week == -1) {
                week = getWeekOfDate(calDate);
            } else {
                week = (week % 7);
            }
            int week1 = getWeekOfDate(calDate);
            //判断该天是否为工作日
            int isWorkDay = getIsWorkDay(week);
            int isWorkDay1 = getIsWorkDay(week1);
            System.out.println(DateUtil.date2String(calDate) + ":" +week + ":" + isWorkDay);
            System.out.println(DateUtil.date2String(calDate) + ":" +week1 + ":" + isWorkDay1);
            // 在租期内，才初始化租期内的第几天
            week ++;
        }
    }
    
    public static int getWeekOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }
    
    public static int getIsWorkDay(int week) {
        int isWorkDay;
        if (week == 5
                || week == 6
                || week == 0) {
            isWorkDay = 0;
        } else {
            isWorkDay = 1;
        }
        return isWorkDay;
    }
}
