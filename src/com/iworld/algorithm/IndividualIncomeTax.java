package com.iworld.algorithm;

/**
 * @author gq.cai
 * @version 1.0
 * @description 个税收入计算
 * @date 2023/3/3 14:12
 */
public class IndividualIncomeTax {
    
    private static final int FIRST_FLOR = 36000;
    private static final int SECOND_FLOR = 144000;
    private static final int THIRD_FLOR = 300000;
    private static final int FOURTH_FLOR = 420000;
    private static final int FIFTH_FLOR = 660000;
    private static final int SIXTH_FLOR = 960000;
    
    private static final int FIRST_FLOR_TAX_RATE = 3;
    private static final int SECOND_FLOR_TAX_RATE = 10;
    private static final int THIRD_FLOR_TAX_RATE = 20;
    private static final int FOURTH_FLOR_TAX_RATE = 25;
    private static final int FIFTH_FLOR_TAX_RATE = 30;
    private static final int SIXTH_FLOR_TAX_RATE = 35;
    private static final int SEVENTH_FLOR_TAX_RATE = 45;
    
    private static final int FIRST_FLOR_DEDUCT = 2520;
    private static final int SECOND_FLOR_DEDUCT = 16920;
    private static final int THIRD_FLOR_DEDUCT = 31920;
    private static final int FOURTH_FLOR_DEDUCT = 52920;
    private static final int FIFTH_FLOR_DEDUCT = 85920;
    private static final int SIXTH_FLOR_DEDUCT = 181920;
    
    private static final double MOST_FUNDS = 3826;
    
    private static final int BASE = 5000;
    
    public static void main(String[] args) {
        double salary = 60000;
        double v = calcStandard(salary, 12, 3500);
        System.out.println(v);
        double actualIncome = actualIncome(salary, 12, 3500);
        System.out.println(actualIncome);
        System.out.println(baseCalc(112328.1));
        System.out.println(baseCalc(112233.32));
        System.out.println(baseCalc(112233.32));
        System.out.println(baseCalc(3443.45));
    }
    
    public static double calc(double salary, double insure, double funds) {
        double base = salary - BASE - insure - funds;
        return baseCalc(base);
    }
    
    /**
     *
     * @param base 实际交税金额
     * @return
     */
    public static double baseCalc(double base) {
        if (base <= 0) {
            return 0;
        }else if (base <= FIRST_FLOR) {
            return base * FIRST_FLOR_TAX_RATE / 100;
        } else if (base <= SECOND_FLOR) {
            return base * SECOND_FLOR_TAX_RATE / 100 - FIRST_FLOR_DEDUCT;
        } else if (base <= THIRD_FLOR) {
            return base * THIRD_FLOR_TAX_RATE / 100 - SECOND_FLOR_DEDUCT;
        } else if (base <= FOURTH_FLOR) {
            return base * FOURTH_FLOR_TAX_RATE / 100 - THIRD_FLOR_DEDUCT;
        } else if (base <= FIFTH_FLOR) {
            return base * FIFTH_FLOR_TAX_RATE / 100 - FOURTH_FLOR_DEDUCT;
        } else if (base <= SIXTH_FLOR) {
            return base * SIXTH_FLOR_TAX_RATE / 100 - FIFTH_FLOR_DEDUCT;
        }
        return base * SEVENTH_FLOR_TAX_RATE / 100 - SIXTH_FLOR_DEDUCT;
    }
    
    public static double actualIncome(double salary, double fundsRate, double otherDeduct) {
        double funds = salary * fundsRate / 100;
        double base = (salary - (salary * 10.2 / 100) - (Math.min(funds, MOST_FUNDS)) - BASE - otherDeduct) * 12;
        double tax = baseCalc(base);
        return base - tax;
    }
    /**
     *
     * @param salary
     * @return
     */
    public static double calcStandard(double salary, double fundsRate, double otherDeduct) {
        double funds = salary * fundsRate / 100;
        double base = (salary - (salary * 10.2 / 100) - (Math.min(funds, MOST_FUNDS)) - BASE - otherDeduct) * 12;
        return baseCalc(base);
    }
}
