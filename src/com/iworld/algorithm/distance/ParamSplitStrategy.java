package com.iworld.algorithm.distance;

import com.iworld.algorithm.distance.pojo.ApiParam;

import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 16:42
 */
public interface ParamSplitStrategy <T extends ApiParam> {
    
    List<T> split(T t);
}
