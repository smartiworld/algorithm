package com.iworld.algorithm.distance.service;

import com.iworld.algorithm.distance.MapApiService;
import com.iworld.algorithm.distance.enums.MapApiTypeEnum;
import com.iworld.algorithm.distance.pojo.ApiParam;
import com.iworld.algorithm.distance.pojo.MultiDestParam;
import com.iworld.algorithm.distance.pojo.MultiOriginParam;
import com.iworld.algorithm.distance.pojo.MultiPairParam;
import com.iworld.algorithm.distance.pojo.Result;
import com.iworld.algorithm.distance.pojo.RouteMatrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 16:33
 */
public abstract class AbstractApiCalService implements MapApiService {
    
    @Override
    public Result<RouteMatrix> calMultiDest(MultiDestParam param) {
        return null;
    }
    
    @Override
    public Result<RouteMatrix> calMultiOrigin(MultiOriginParam param) {
        return null;
    }
    
    @Override
    public Result<RouteMatrix> calMultiPair(MultiPairParam param) {
        return null;
    }
    
    protected <T extends ApiParam> Result<RouteMatrix> calculate(T param ){
        
        Result<RouteMatrix> result = new Result<RouteMatrix>();
        
        try {
//            T newParam = MapApiUtil.assertParam(param);
//
//            ParamSplitStrategyV2<T> splitStrategyV2 = getParamSplictStrategy();
//
//            List<T> params = splitStrategyV2.split(newParam);
    
            RouteMatrix routeMatrix = null;
            
            result.setStatus(Result.REMOTE_SUCCES_STATUS);
            result.setRe(routeMatrix);
        } catch (Exception e) {
            result.setStatus(Result.REMOTE_SERVICE_ERROR_STATUS);
            result.setMsg(e.getMessage());
        }
        return result;
        
    }
    
    /**
     * 获取接口类型
     * @return
     */
    protected abstract MapApiTypeEnum getApiType();
    
    /**
     * 参数　自动拆分策略
     * @return
     */
//    protected   <T extends ApiParam> ParamSplitStrategy<T> getParamSplictStrategy(){
//        return StrategyUtil.getParamSplitStragety(getApiType());
//    }
}
