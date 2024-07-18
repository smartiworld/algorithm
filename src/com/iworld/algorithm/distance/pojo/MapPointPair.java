package com.iworld.algorithm.distance.pojo;

import java.io.Serializable;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 16:27
 */
public class MapPointPair implements Serializable {
    
    private static final long serialVersionUID = -6901611613768066179L;
    /**
     * 起点
     */
    private MapPoint start;
    /**
     * 终点
     */
    private MapPoint end;
    /**
     * 索引顺序
     */
    private int idx;
    
    public MapPoint getStart() {
        return start;
    }
    
    public void setStart(MapPoint start) {
        this.start = start;
    }
    
    public MapPoint getEnd() {
        return end;
    }
    
    public void setEnd(MapPoint end) {
        this.end = end;
    }
    
    public int getIdx() {
        return idx;
    }
    
    public void setIdx(int idx) {
        this.idx = idx;
    }
}
