package com.iworld.algorithm.distance.service;

import com.iworld.algorithm.distance.ParamSplitStrategy;
import com.iworld.algorithm.distance.pojo.ApiParam;

import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 16:43
 */
public class GolderDistanceSplitStrategy <T extends ApiParam> implements ParamSplitStrategy<T> {
    
    @Override
    public List<T> split(T t) {
        List<T> res = null;
//        if (t instanceof MultiOriginParam) {
//            MultiOriginParam multiOriginParam = (MultiOriginParam) t;
//            res =  (List<T>) ParamSplitUtil.splitPrams(multiOriginParam , LbsConstants.BATCH_NUM);
//        }else if (t instanceof MultiDestParam) {
//            res =  (List<T>) ParamSplitUtil.splitParam2SinglePairs(t);
//        }else if (t instanceof MultiPairParam) {
//            res =  (List<T>) ParamSplitUtil.splitParam2SinglePairs(t);
//        }
//        ParamSplitUtil.setStragety(res, t.getDrivingStrategyEnum());
    
        return res;
    }
}
