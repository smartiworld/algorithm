package com.iworld.algorithm.insurance;

import java.util.Arrays;
import java.util.List;


public enum InsurancePackageEnum implements EnumValue {

    /**
     * 各收费项目
     */
    WHIHOUT_BUY(0, "未购买出行保障套餐", null),
    EXCLUSIVE_DEDUCTIBLE_BUY(1, "尊享服务", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode())),
    EXCLUSIVE_DEDUCTIBLE_AND_MILLION_INSURANCE_BUY(2, "尊享百万服务", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.MILLION_INSURANCE_FEE.getCode())),
    EXCLUSIVE_DEDUCTIBLE_AND_DRIVE_PROTECT_BUY(3, "尊享驾乘守护", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode())),
    ALL_BUY(4, "全程无忧", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.MILLION_INSURANCE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode())),
    ALL_PLUS_BUY(6, "全程无忧升级版", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode())),
    EXCLUSIVE_DEDUCTIBLE_PLUS_BUY(7, "尊享服务升级版", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.DRIVER_PROTECT_SERVICE_FEE.getCode(), ChargeItemEnum.ATTRACT_USER_SERVICE_FEE.getCode()));
    
    /**
     * index
     */
    private int index;

    /**
     * 名称
     */
    private String name;

    /**
     * 出行保障套餐明细-收费项目code集合
     */
    private List<String> insurancePackageDetailList;


    InsurancePackageEnum(int index, String name, List<String> insurancePackageDetailList) {
        this.index = index;
        this.name = name;
        this.insurancePackageDetailList = insurancePackageDetailList;
    }

    /**
     * description: 根据index得到对应出行保障套餐明细
     *
     * @param index index
     * @return InsurancePackageDetailList
     */
    public static List<String> getInsurancePackageDetailListByIndex(int index) {
        for (InsurancePackageEnum operateTypeEnum : InsurancePackageEnum.values()) {
            if (operateTypeEnum.getIndex() == index) {
                return operateTypeEnum.insurancePackageDetailList;
            }
        }
        return null;
    }

    /**
     * description: 根据index得到对应名称
     *
     * @param index index
     * @return 枚举名称
     */
    public static String getNameByIndex(int index) {
        for (InsurancePackageEnum operateTypeEnum : InsurancePackageEnum.values()) {
            if (operateTypeEnum.getIndex() == index) {
                return operateTypeEnum.name;
            }
        }
        return null;
    }

    public static InsurancePackageEnum getEnumByIndex(int index) {
        for (InsurancePackageEnum operateTypeEnum : InsurancePackageEnum.values()) {
            if (operateTypeEnum.getIndex() == index) {
                return operateTypeEnum;
            }
        }
        return null;
    }

    /**
     * description: 根据出行保障套餐明细列表得到对应枚举
     *
     * @param insurancePackageDetailList
     * @return InsurancePackageEnum
     */
    public static InsurancePackageEnum getEnumByInsurancePackageDetailList(List<String> insurancePackageDetailList ) {
        for (InsurancePackageEnum operateTypeEnum : InsurancePackageEnum.values()) {
            List<String> detailList = operateTypeEnum.getInsurancePackageDetailList();
            if (detailList == null) {
                //未购买出行保障套餐时，入参为空返回枚举 WHIHOUT_BUY
                if (insurancePackageDetailList == null || insurancePackageDetailList.size() > 0) {
                    return operateTypeEnum;
                }
            } else if (detailList.size() == insurancePackageDetailList.size() && detailList.containsAll(insurancePackageDetailList)) {
                return operateTypeEnum;
            }
        }
        return null;
    }


    @Override
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInsurancePackageDetailList() {
        return insurancePackageDetailList;
    }

    public void setInsurancePackageDetailList(List<String> insurancePackageDetailList) {
        this.insurancePackageDetailList = insurancePackageDetailList;
    }
}
