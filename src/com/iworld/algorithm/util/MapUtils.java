package com.iworld.algorithm.util;

import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2023/12/20 19:38
 */
public class MapUtils {
    
    public static <T, K> void printMap(Map<T, K> map) {
        int i = 0;
        for (Map.Entry<T, K> entry : map.entrySet()) {
            if (i == 0) {
                System.out.print("{");
            }
            System.out.print(entry.getKey() + ":" + entry.getValue());
            if (i != map.size() - 1) {
                System.out.print(",");
            } else {
                System.out.println("}");
            }
            i++;
        }
    }
}
