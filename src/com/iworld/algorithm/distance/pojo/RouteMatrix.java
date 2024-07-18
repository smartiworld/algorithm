package com.iworld.algorithm.distance.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 11:23
 */
public class RouteMatrix implements Serializable {
    
    private static final long serialVersionUID = 8266412056522164666L;
    
    /**
     * route 排序是与参数一一对应关系
     * 单起点　多终点　按照终点排序
     * 多起点　单终点　按照起点排序
     * 多起点　多终点　成对使用，按照数据对列表顺序排序
     *
     * 若 记录计算　时长　返回值距离小于０　表示计算失败
     */
    private List<Route> routes;
    
    
    public List<Route> getRoutes() {
        return routes;
    }
    
    
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
