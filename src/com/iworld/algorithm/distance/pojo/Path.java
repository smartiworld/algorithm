package com.iworld.algorithm.distance.pojo;

import java.io.Serializable;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 11:18
 */
public class Path implements Serializable {
    
    private static final long serialVersionUID = 6332036415408570599L;
    
    /**
     * 行驶距离 米
     */
    private Integer distance;
    
    /**
     * 预计行驶时间 秒
     */
    private Integer duration;
    
    /**
     * 导航策略
     * DrivingStrategyEnum
     */
    private String strategy;
    
    /**
     * 此导航方案道路收费 单位：元
     */
    private Double tolls;
    /**
     *  限行结果 0 代表限行已规避或未限行,即该路线没有限行路段
     1 代表限行无法规避,即该线路有限行路段
     */
    private Integer restriction;
    
    /**
     * 收费路段距离
     */
    private Double toll_distance;
    
    public Integer getDistance() {
        return distance;
    }
    
    public void setDistance(Integer distance) {
        this.distance = distance;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    
    
    public String getStrategy() {
        return strategy;
    }
    
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
    
    public Double getTolls() {
        return tolls;
    }
    
    public void setTolls(Double tolls) {
        this.tolls = tolls;
    }
    
    public Integer getRestriction() {
        return restriction;
    }
    
    public void setRestriction(Integer restriction) {
        this.restriction = restriction;
    }
    
    public Double getToll_distance() {
        return toll_distance;
    }
    
    public void setToll_distance(Double toll_distance) {
        this.toll_distance = toll_distance;
    }
}
