package com.iworld.algorithm.distance.pojo;

import com.iworld.algorithm.distance.enums.DrivingStrategyEnum;

import java.io.Serializable;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 11:29
 */
public class ApiParam implements Serializable {
    
    private static final long serialVersionUID = 468140492597450230L;
    
    private DrivingStrategyEnum drivingStrategyEnum;
    
    public DrivingStrategyEnum getDrivingStrategyEnum() {
        return drivingStrategyEnum;
    }
    
    public void setDrivingStrategyEnum(DrivingStrategyEnum drivingStrategyEnum) {
        this.drivingStrategyEnum = drivingStrategyEnum;
    }
}
