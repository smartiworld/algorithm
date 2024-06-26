package com.iworld.algorithm;

import com.iworld.algorithm.enums.DataTypeEnum;
import com.iworld.algorithm.enums.ScopeTypeEnum;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/6/25 10:39
 */
public class PriorityCode {
    
    public static int getPriceCalcPriorityCode(Integer scopeType, Integer dataType, Long cityId) {
        int code = 0;
        if (cityId != null && cityId > -1) {
            code = 1;
        }
        code <<= 2;
        if (DataTypeEnum.MODEL.getIndex().equals(dataType)) {
            code |= 2;
        } else if (DataTypeEnum.MODEL_GROUP.getIndex().equals(dataType)) {
            code |= 1;
        }
        code <<= 1;
        if (ScopeTypeEnum.SPECIAL_SCOPE.getIndex().equals(scopeType)) {
            code |= 1;
        }
        return code;
    }
    
    public static void main(String[] args) {
        Long[] cityIds = {-1L, 1L};
        for (Long cityId : cityIds) {
            for (DataTypeEnum dataTypeEnum : DataTypeEnum.values()) {
                for (ScopeTypeEnum scopeTypeEnum : ScopeTypeEnum.values()) {
                    System.out.println(cityId + "===" + dataTypeEnum.getName() + "===" + scopeTypeEnum.getDesc());
                    System.out.println(getPriceCalcPriorityCode(scopeTypeEnum.getIndex(), dataTypeEnum.getIndex(), cityId));
                }
            }
        }
    }
}
