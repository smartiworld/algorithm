package com.iworld.algorithm.distance.pojo;

import java.io.Serializable;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 11:22
 */
public class MapPoint implements Serializable {
    
    private static final long serialVersionUID = 2390750182218600320L;
    /**
     * 唯一标示　可空
     */
    private Object id;
    
    /**
     * 经度
     */
    private Double lon;
    /**
     * 维度
     */
    private Double lat;
    
    /**
     * 参数索引值　内部服务调用使用
     * 外部不要设置
     */
    private int idx;
    
    /**
     * 车牌号　省缩写
     */
    private String province;
    
    /**
     * 车牌号　省缩写后字段
     */
    private String number;
    
    public Object getId() {
        return id;
    }
    
    public void setId(Object id) {
        this.id = id;
    }
    
    public Double getLon() {
        return lon;
    }
    
    public void setLon(Double lon) {
        this.lon = lon;
    }
    
    public Double getLat() {
        return lat;
    }
    
    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    public int getIdx() {
        return idx;
    }
    
    public void setIdx(int idx) {
        this.idx = idx;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
}
