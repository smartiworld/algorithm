package com.iworld.algorithm.distance.pojo;

import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 单起点　多终点参数
 * @date 2024/4/8 11:28
 */
public class MultiDestParam extends ApiParam {
    
    private static final long serialVersionUID = 6789633321885901947L;
    
    private MapPoint origin;
    
    private List<MapPoint> destinations;
    
    public MapPoint getOrigin() {
        return origin;
    }
    
    public void setOrigin(MapPoint origin) {
        this.origin = origin;
    }
    
    public List<MapPoint> getDestinations() {
        return destinations;
    }
    
    public void setDestinations(List<MapPoint> destinations) {
        this.destinations = destinations;
    }
}
