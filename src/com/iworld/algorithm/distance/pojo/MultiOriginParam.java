package com.iworld.algorithm.distance.pojo;

import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 多起点　单终点
 * @date 2024/4/8 13:52
 */
public class MultiOriginParam extends ApiParam {
    
    private static final long serialVersionUID = -3756015570008095598L;
    /**
     * 起点
     */
    private List<MapPoint> origins;
    /**
     * 终点
     */
    private MapPoint destination;
    
    public List<MapPoint> getOrigins() {
        return origins;
    }
    
    public void setOrigins(List<MapPoint> origins) {
        this.origins = origins;
    }
    
    public MapPoint getDestination() {
        return destination;
    }
    
    
    public void setDestination(MapPoint destination) {
        this.destination = destination;
    }
}
