package com.iworld.algorithm.insurance;

import java.util.Arrays;
import java.util.List;

public enum InsurancePackageBitEnum implements EnumValue {
    
    /**
     * 各收费项目
     */
    WHIHOUT_BUY(0, "未购买出行保障套餐", null, TrueOrFalse.FALSE.getValue()),
    EXCLUSIVE_DEDUCTIBLE_BUY(1, "尊享服务", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode())),
    EXCLUSIVE_DEDUCTIBLE_AND_MILLION_INSURANCE_BUY(3, "尊享百万服务", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.MILLION_INSURANCE_FEE.getCode())),
    EXCLUSIVE_DEDUCTIBLE_AND_DRIVE_PROTECT_BUY(9, "尊享驾乘守护", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode())),
    ALL_BUY(11, "全程无忧", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.MILLION_INSURANCE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode()), TrueOrFalse.TRUE.getValue(), TrueOrFalse.TRUE.getValue()),
    ALL_PLUS_BUY(13, "全程无忧升级版", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode()), TrueOrFalse.TRUE.getValue(), TrueOrFalse.TRUE.getValue()),
    EXCLUSIVE_DEDUCTIBLE_PLUS_BUY(49, "尊享服务升级版", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.DRIVER_PROTECT_SERVICE_FEE.getCode(), ChargeItemEnum.ATTRACT_USER_SERVICE_FEE.getCode())),
    
    INSURANCE_ALL_BUY(63, "全部保险套餐", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(),  ChargeItemEnum.MILLION_INSURANCE_FEE.getCode(), ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode(), ChargeItemEnum.DRIVER_PROTECT_SERVICE_FEE.getCode(), ChargeItemEnum.ATTRACT_USER_SERVICE_FEE.getCode()), TrueOrFalse.FALSE.getValue()),
    
    EXCLUSIVE_DEDUCTIBLE_ENHANCE_BUY(65, "尊享服务2025", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.THREE_HUNDRED_THOUSAND_INSURANCE_FEE.getCode()), TrueOrFalse.TRUE.getValue(), TrueOrFalse.FALSE.getValue(), TrueOrFalse.TRUE.getValue()),
    EXCLUSIVE_DEDUCTIBLE_ENHANCE_PLUS_BUY(16489, "尊享服务升级版2025", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.THREE_HUNDRED_THOUSAND_INSURANCE_FEE.getCode(), ChargeItemEnum.ATTRACT_USER_SERVICE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode(), ChargeItemEnum.OUTSIDE_MEDICAL_INSURANCE_10_FEE.getCode()), TrueOrFalse.TRUE.getValue(), TrueOrFalse.FALSE.getValue(), TrueOrFalse.TRUE.getValue()),
    
    EXCLUSIVE_DEDUCTIBLE_AND_DRIVE_PROTECT_ENHANCER_BUY(585, "驾乘守护2025", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.THREE_HUNDRED_THOUSAND_INSURANCE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_10_FEE.getCode()), TrueOrFalse.TRUE.getValue(), TrueOrFalse.FALSE.getValue(), TrueOrFalse.TRUE.getValue()),
    EXCLUSIVE_DEDUCTIBLE_AND_DRIVE_PROTECT_ENHANCER_PLUS_BUY(21609, "驾乘守护升级版2025", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.THREE_HUNDRED_THOUSAND_INSURANCE_FEE.getCode(), ChargeItemEnum.TWO_HUNDRED_THOUSAND_DRIVER_PROTECT_SERVICE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_20_FEE.getCode(), ChargeItemEnum.ATTRACT_USER_SERVICE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode(), ChargeItemEnum.OUTSIDE_MEDICAL_INSURANCE_10_FEE.getCode()), TrueOrFalse.TRUE.getValue(), TrueOrFalse.FALSE.getValue(), TrueOrFalse.TRUE.getValue()),
    
    ZERO_WORRIES_ENHANCER_BUY(649, "全程无忧2025", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.ONE_AND_A_HALF_MILLION_INSURANCE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_10_FEE.getCode()), TrueOrFalse.TRUE.getValue(), TrueOrFalse.TRUE.getValue(), TrueOrFalse.TRUE.getValue()),
    ZERO_WORRIES_ENHANCER_PLUS_BUY(26921, "全程无忧升级版2025", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.THREE_MILLION_INSURANCE_FEE.getCode(), ChargeItemEnum.HALF_A_MILLION_DRIVER_PROTECT_SERVICE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_50_FEE.getCode(), ChargeItemEnum.ATTRACT_USER_SERVICE_FEE.getCode(), ChargeItemEnum.ATTRACT_USER_SERVICE_FEE.getCode(), ChargeItemEnum.DRIVE_PROTECT_FEE.getCode(), ChargeItemEnum.OUTSIDE_MEDICAL_INSURANCE_10_FEE.getCode()), TrueOrFalse.TRUE.getValue(), TrueOrFalse.TRUE.getValue(), TrueOrFalse.TRUE.getValue()),
    
    EXCLUSIVE_DEDUCTIBLE_AND_MILLION_ENHANCE_PLUS_BUY(14, "尊享服务升级版", Arrays.asList(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), ChargeItemEnum.MILLION_INSURANCE_FEE.getCode(), ChargeItemEnum.ATTRACT_USER_SERVICE_FEE.getCode(), ChargeItemEnum.DRIVER_PROTECT_SERVICE_FEE.getCode(), ChargeItemEnum.OUTSIDE_MEDICAL_INSURANCE_10_FEE.getCode()), TrueOrFalse.TRUE.getValue(), TrueOrFalse.FALSE.getValue(), TrueOrFalse.TRUE.getValue(), 14),
    ;
    
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
    
    private int enhancerFlag;
    
    private int useDiscountFlag;
    
    private int needCalcFlag;
    
    private int code;
    
    private Integer plusCode;
    
    InsurancePackageBitEnum(int index, String name, List<String> insurancePackageDetailList) {
        this.index = index;
        this.name = name;
        this.insurancePackageDetailList = insurancePackageDetailList;
        this.enhancerFlag = 0;
        this.needCalcFlag = 1;
        this.useDiscountFlag = 0;
    }
    
    InsurancePackageBitEnum(int index, String name, List<String> insurancePackageDetailList, int needCalcFlag) {
        this.index = index;
        this.name = name;
        this.insurancePackageDetailList = insurancePackageDetailList;
        this.enhancerFlag = 0;
        this.needCalcFlag = needCalcFlag;
        this.useDiscountFlag = 0;
    }
    
    InsurancePackageBitEnum(int index, String name, List<String> insurancePackageDetailList, int needCalcFlag, int useDiscountFlag) {
        this.index = index;
        this.name = name;
        this.insurancePackageDetailList = insurancePackageDetailList;
        this.enhancerFlag = 0;
        this.useDiscountFlag = useDiscountFlag;
        this.needCalcFlag = needCalcFlag;
    }
    
    InsurancePackageBitEnum(int index, String name, List<String> insurancePackageDetailList, int needCalcFlag, int useDiscountFlag, int enhancerFlag) {
        this.index = index;
        this.name = name;
        this.insurancePackageDetailList = insurancePackageDetailList;
        this.enhancerFlag = enhancerFlag;
        this.useDiscountFlag = useDiscountFlag;
        this.needCalcFlag = needCalcFlag;
    }
    
    InsurancePackageBitEnum(int index, String name, List<String> insurancePackageDetailList, int needCalcFlag, int useDiscountFlag, int enhancerFlag, int code, Integer plusCode) {
        this.index = index;
        this.name = name;
        this.insurancePackageDetailList = insurancePackageDetailList;
        this.enhancerFlag = enhancerFlag;
        this.useDiscountFlag = useDiscountFlag;
        this.needCalcFlag = needCalcFlag;
        this.code = code;
        this.plusCode = plusCode;
    }
    
    InsurancePackageBitEnum(int index, String name, List<String> insurancePackageDetailList, int needCalcFlag, int useDiscountFlag, int enhancerFlag, int code) {
        this.index = index;
        this.name = name;
        this.insurancePackageDetailList = insurancePackageDetailList;
        this.enhancerFlag = enhancerFlag;
        this.useDiscountFlag = useDiscountFlag;
        this.needCalcFlag = needCalcFlag;
        this.code = code;
    }
    
    /**
     * description: 根据index得到对应出行保障套餐明细
     *
     * @param index index
     * @return InsurancePackageDetailList
     */
    public static List<String> getInsurancePackageDetailListByIndex(int index) {
        for (InsurancePackageBitEnum operateTypeEnum : InsurancePackageBitEnum.values()) {
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
        for (InsurancePackageBitEnum operateTypeEnum : InsurancePackageBitEnum.values()) {
            if (operateTypeEnum.getIndex() == index) {
                return operateTypeEnum.name;
            }
        }
        return null;
    }
    
    public static InsurancePackageBitEnum getEnumByIndex(int index) {
        for (InsurancePackageBitEnum operateTypeEnum : InsurancePackageBitEnum.values()) {
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
    public static InsurancePackageBitEnum getEnumByInsurancePackageDetailList(List<String> insurancePackageDetailList ) {
        for (InsurancePackageBitEnum operateTypeEnum : InsurancePackageBitEnum.values()) {
            List<String> detailList = operateTypeEnum.getInsurancePackageDetailList();
            if (detailList == null) {
                //未购买出行保障套餐时，入参为空返回枚举 WHIHOUT_BUY
                if (insurancePackageDetailList == null || insurancePackageDetailList.size() == 0) {
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
    
    public int getEnhancerFlag() {
        return enhancerFlag;
    }
    
    public void setEnhancerFlag(int enhancerFlag) {
        this.enhancerFlag = enhancerFlag;
    }
    
    public int getUseDiscountFlag() {
        return useDiscountFlag;
    }
    
    public void setUseDiscountFlag(int useDiscountFlag) {
        this.useDiscountFlag = useDiscountFlag;
    }
    
    public int getNeedCalcFlag() {
        return needCalcFlag;
    }
    
    public void setNeedCalcFlag(int needCalcFlag) {
        this.needCalcFlag = needCalcFlag;
    }
    
    public static void main(String[] args) {
        List<String> insurancePackageDetailList = InsurancePackageBitEnum.EXCLUSIVE_DEDUCTIBLE_AND_MILLION_ENHANCE_PLUS_BUY.getInsurancePackageDetailList();
        InsurancePackageUtil.
    }
    
}
