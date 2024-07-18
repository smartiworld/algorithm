package com.iworld.algorithm.distance.pojo;

import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 多起点 多终点
 * @date 2024/4/8 16:26
 */
public class MultiPairParam extends ApiParam {
    
    private static final long serialVersionUID = -79884130823139472L;
    
    private List<MapPointPair> pairs;
    
    public List<MapPointPair> getPairs() {
        return pairs;
    }
    
    public void setPairs(List<MapPointPair> pairs) {
        this.pairs = pairs;
    }
}
