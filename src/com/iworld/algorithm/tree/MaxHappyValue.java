package com.iworld.algorithm.tree;

import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 最大快乐值
 * 公司举办party 可以决定一些员工来和一些员工不来
 * 规则：1.某个员工来了 下属员工就不可以再来
 * 2.party的快乐值为所来员工快乐值的和
 * 目的 求出所来员工最大快乐值
 * 每个员工可以有多个下级 公司唯一老板 基层员工不在有下属
 * @date 2022/5/1 23:26
 */
public class MaxHappyValue {
    
    public static int getMaxHappyValue(Employee employee) {
        if (employee == null) {
            return 0;
        }
        EmployeeInfo employeeInfo = process(employee);
        // 决策出来和不来最大快乐值
        return Math.max(employeeInfo.comeMaxHappyValue, employeeInfo.unComeMaxHappyValue);
    }
    
    private static EmployeeInfo process(Employee head) {
        if (head.employees == null || head.employees.isEmpty()) {
            return new EmployeeInfo(head.happyValue, 0);
        }
        // 当前员工来参加的快乐值 当前员工快乐值加下属员工不来快乐值
        int comeMaxHappyValue = head.happyValue;
        int unComeMaxHappyValue = 0;
        if (head.employees != null) {
            for (Employee employee : head.employees) {
                EmployeeInfo employeeInfo = process(employee);
                comeMaxHappyValue += employeeInfo.unComeMaxHappyValue;
                // 当前员工不来的时候最大快乐值 当前员工不来的时候 下属员工可以来和可以不来 从来和不来找出最大值
                unComeMaxHappyValue += Math.max(employeeInfo.comeMaxHappyValue, employeeInfo.unComeMaxHappyValue);
            }
        }
        return new EmployeeInfo(comeMaxHappyValue, unComeMaxHappyValue);
    }
    
    static class EmployeeInfo {
        // 来最大快乐值
        int comeMaxHappyValue;
        // 不来最大快乐值
        int unComeMaxHappyValue;
        
        EmployeeInfo(int comeMaxHappyValue, int unComeMaxHappyValue) {
            this.comeMaxHappyValue = comeMaxHappyValue;
            this.unComeMaxHappyValue = unComeMaxHappyValue;
        }
    }
    static class Employee {
        // 员工快乐值
        int happyValue;
        // 员工下属员工
        List<Employee> employees;
    }
}
