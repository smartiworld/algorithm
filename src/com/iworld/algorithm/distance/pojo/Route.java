package com.iworld.algorithm.distance.pojo;

import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 11:17
 */
public class Route {
    
    /**
     *
     */
    private static final long serialVersionUID = 4310037184917908445L;
    
    /**
     * 排序索引　内部计算使用
     */
    private int idx;
    
    /**
     * 起点　
     */
    private MapPoint origin;
    /**
     * 终点
     */
    private MapPoint destination;
    
    /**
     * 距离(精确到米)　若返回值距离小于０　表示计算失败
     */
    private int distanceValue = -1;
    
    /**
     * 时长(精确到秒)　若返回值距离小于０　表示计算失败
     */
    private int durationValue = -1;
    
    /**
     * 路径规划 线路
     */
    private List<Path> paths;
    
    /**
     * distance出错时候　会显示此字段
     */
    private String info;
    
    /**
     * distance出错时候　会显示此字段
     */
    private String code;
    
    
    public int getIdx() {
        return idx;
    }
    
    public void setIdx(int idx) {
        this.idx = idx;
    }
    
    public MapPoint getOrigin() {
        return origin;
    }
    
    public void setOrigin(MapPoint origin) {
        this.origin = origin;
    }
    
    public MapPoint getDestination() {
        return destination;
    }
    
    public void setDestination(MapPoint destination) {
        this.destination = destination;
    }
    
    public int getDistanceValue() {
        return distanceValue;
    }
    
    
    public void setDistanceValue(int distanceValue) {
        this.distanceValue = distanceValue;
    }
    
    
    public int getDurationValue() {
        return durationValue;
    }
    
    
    public void setDurationValue(int durationValue) {
        this.durationValue = durationValue;
    }
    
    
    public List<Path> getPaths() {
        return paths;
    }
    
    
    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }
    
    
    public String getInfo() {
        return info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
}
