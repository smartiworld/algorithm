package com.iworld.algorithm.util;

import com.iworld.algorithm.list.CommonDoubleList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/1/9 17:41
 */
public class CommonDoubleListUtils {
    
    public static <T> List<Object> serializerDoubleList(CommonDoubleList<T> list) {
        if (list == null) {
            return null;
        }
        List<T> ans1 = new ArrayList<>();
        ans1.add(list.value);
        CommonDoubleList<T> pre = null;
        while (list.next != null) {
            ans1.add(list.next.value);
            list = list.next;
        }
        List<T> ans2 = new ArrayList<>();
        while (list != null) {
            ans2.add(list.value);
            list = list.pre;
        }
        List<Object> ans = new ArrayList<>();
        ans.add(ans1);
        ans.add(ans2);
        return ans;
    }
}
