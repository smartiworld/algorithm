package com.iworld.algorithm.distance;

import com.iworld.algorithm.distance.pojo.MultiDestParam;
import com.iworld.algorithm.distance.pojo.MultiOriginParam;
import com.iworld.algorithm.distance.pojo.MultiPairParam;
import com.iworld.algorithm.distance.pojo.Result;
import com.iworld.algorithm.distance.pojo.RouteMatrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 11:16
 */
public interface MapApiService {
    
    /**
     * 单起点　多终点计算
     * 返回列表按照　起点列表顺序排序，
     * @param param
     * @return
     */
    Result<RouteMatrix> calMultiDest(MultiDestParam param);
    
    /**
     * 多起点　单终点计算
     * 返回列表按照　终点列表顺序排序，
     * @param param
     * @return
     */
    Result<RouteMatrix>  calMultiOrigin(MultiOriginParam param);
    
    
    /**
     * 多起点　多终点　成对计算
     * 返回列表按照　列表顺序排序，
     * @param param
     * @return
     */
    Result<RouteMatrix> calMultiPair(MultiPairParam param);
}
