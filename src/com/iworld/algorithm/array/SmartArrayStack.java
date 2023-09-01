package com.iworld.algorithm.array;

import java.math.BigDecimal;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/8/10 17:50
 */
public class SmartArrayStack<E> {

    Object[] items;
    private int capacity;
    private int count;
    
    public SmartArrayStack(int capacity) {
        this.capacity = capacity;
        items = new Object[capacity];
    }
    
    public void push(E e) {
        if (count >= capacity) {
            throw new RuntimeException("栈已满");
        }
        items[count] = e;
        count ++;
    }
    
    public E pop() {
        if (count == 0) {
            throw new RuntimeException("栈已空");
        }
        E e = (E) items[count];
        items[count] = null;
        count --;
        return null;
    }
    
    
    
    public static void main(String[] args) {
        BigDecimal calcPrice1 = getCalcPrice(7, 22);
        BigDecimal calcPrice2 = getCalcPrice(6, 22);
        BigDecimal calcPrice3 = getCalcPrice(5, 22);
        System.out.println("calcPrice1===" + calcPrice1.doubleValue() + ",calcPrice2===" + calcPrice2.doubleValue() + ",calcPrice3===" + calcPrice3.doubleValue());
    }
    
    public static BigDecimal getCalcPrice(int everDayPrice, int day) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (int i = 0; i < day * 2; i++) {
            BigDecimal bEverDayPrice = BigDecimal.valueOf(everDayPrice);
            if (totalPrice.compareTo(new BigDecimal(100)) >= 0)  {
                bEverDayPrice = bEverDayPrice.multiply(BigDecimal.valueOf((80)).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP));
            }
            if (totalPrice.compareTo(new BigDecimal(150)) >= 0)  {
                bEverDayPrice = bEverDayPrice.multiply(BigDecimal.valueOf((50)).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP));
            }
            totalPrice = totalPrice.add(bEverDayPrice);
        }
        return totalPrice;
    }
}
