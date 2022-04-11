package com.iworld.algorithm.unionset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 统计有效用户数量
 * 用户有三个字段 其中只要有一个字段相同即为同一用户
 * @date 2022/4/11 22:58
 */
public class CountUserNum {
    
    
    public int getUserNum(List<User> users) {
        UnionSet<User> unionSet = new UnionSet<>(users);
        Map<String, User> phoneMap = new HashMap<>();
        Map<String, User> idMap = new HashMap<>();
        Map<String, User> accountMap = new HashMap<>();
        for (User user : users) {
            // 如果两个用户有相同手机号 合并两个用户到一个集合
            if (phoneMap.get(user.phone) != null) {
                unionSet.union(phoneMap.get(user.phone), user);
            } else {
                phoneMap.put(user.phone, user);
            }
            // 如果两个用户有相同身份证号 合并两个用户到一个集合
            if (idMap.get(user.id) != null) {
                unionSet.union(idMap.get(user.id), user);
            } else {
                idMap.put(user.id, user);
            }
            // 如果两个用户有相同账号 合并两个用户到一个集合
            if (accountMap.get(user.account) != null) {
                unionSet.union(accountMap.get(user.account), user);
            } else {
                accountMap.put(user.account, user);
            }
        }
        return unionSet.size();
    }
    
    public static void main(String[] args) {
        CountUserNum countUserNum = new CountUserNum();
        List<User> users = new ArrayList<>();
        User user1 = new User("123", "ab", "XS");
        users.add(user1);
        User user2 = new User("234", "abc", "XS");
        users.add(user2);
        User user3 = new User("123", "abd", "XSX");
        users.add(user3);
        User user4 = new User("345", "abc", "XXS");
        users.add(user4);
        User user5 = new User("456", "bcd", "XXSS");
        users.add(user5);
        int userNum = countUserNum.getUserNum(users);
        System.out.println(userNum);
    }
    
    static class User {
        private String phone;
        private String id;
        private String account;
        User(String phone, String id, String account) {
            this.account = account;
            this.phone = phone;
            this.id = id;
        }
    }
}
