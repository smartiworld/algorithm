package com.iworld.algorithm.insurance;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InsurancePackageUtil {

    
    public static Integer EXCLUSIVE_DEDUCTIBLE_FEE_NUMBER = 1;
    public static Integer MILLION_INSURANCE_FEE_NUMBER = 1 << 1;
    public static Integer TWO_MILLION_INSURANCE_FEE_NUMBER = 1 << 2;
    public static Integer DRIVE_PROTECT_FEE_NUMBER = 1 << 3;
    public static Integer DRIVER_PROTECT_SERVICE_FEE_NUMBER = 1 << 4;
    public static Integer ATTRACT_USER_SERVICE_FEE_NUMBER = 1 << 5;
    public static Map<String, Integer> insurancePackageBitMap = new HashMap<>();
    
    static {
        insurancePackageBitMap.put(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode(), EXCLUSIVE_DEDUCTIBLE_FEE_NUMBER);
        insurancePackageBitMap.put(ChargeItemEnum.MILLION_INSURANCE_FEE.getCode(), MILLION_INSURANCE_FEE_NUMBER);
        insurancePackageBitMap.put(ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode(), TWO_MILLION_INSURANCE_FEE_NUMBER);
        insurancePackageBitMap.put(ChargeItemEnum.DRIVE_PROTECT_FEE.getCode(), DRIVE_PROTECT_FEE_NUMBER);
        insurancePackageBitMap.put(ChargeItemEnum.DRIVER_PROTECT_SERVICE_FEE.getCode(), DRIVER_PROTECT_SERVICE_FEE_NUMBER);
        insurancePackageBitMap.put(ChargeItemEnum.ATTRACT_USER_SERVICE_FEE.getCode(), ATTRACT_USER_SERVICE_FEE_NUMBER);
    }
    
    
    
    /**
     * 判断是否是否出行保障的收费项目
     *
     * @param itemCode
     * @return
     */
    public static boolean getInsuranceFilter(String itemCode) {
        boolean isReturn = itemCode.equals(ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode())
                || itemCode.equals(ChargeItemEnum.MILLION_INSURANCE_FEE.getCode())
                || itemCode.equals(ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode())
                || itemCode.equals(ChargeItemEnum.DRIVE_PROTECT_FEE.getCode())
                || itemCode.equals(ChargeItemEnum.DRIVER_PROTECT_SERVICE_FEE.getCode())
                || itemCode.equals(ChargeItemEnum.ATTRACT_USER_SERVICE_FEE.getCode());
        return isReturn;
    }

    /**
     * 根据是否购买参数来返回出行保障套餐编码
     *
     * @return 出行保障套餐编码
     */
    public static Integer getInsurancePackage(Integer exclusiveDeductibleFlag, Integer millionInsuranceFeeFlag, Integer twoMillionInsuranceFeeFlag, Integer driveProtectFeeFlag) {
        exclusiveDeductibleFlag = exclusiveDeductibleFlag == null ? 0 : exclusiveDeductibleFlag;
        millionInsuranceFeeFlag = millionInsuranceFeeFlag == null ? 0 : millionInsuranceFeeFlag;
        twoMillionInsuranceFeeFlag = twoMillionInsuranceFeeFlag == null ? 0 : twoMillionInsuranceFeeFlag;
        driveProtectFeeFlag = driveProtectFeeFlag == null ? 0 : driveProtectFeeFlag;
        // 假如是否购买收费项目的入参都为true的话，就返回全程无忧
        if (exclusiveDeductibleFlag.equals(1) && millionInsuranceFeeFlag.equals(1) && driveProtectFeeFlag.equals(1)) {
            return InsurancePackageEnum.ALL_BUY.getIndex();
        } else if (exclusiveDeductibleFlag.equals(1) && twoMillionInsuranceFeeFlag.equals(1) && driveProtectFeeFlag.equals(1)) {
            return InsurancePackageEnum.ALL_PLUS_BUY.getIndex();
        } else {
            Integer packageCode = InsurancePackageEnum.WHIHOUT_BUY.getIndex();

            if (exclusiveDeductibleFlag.equals(0) && millionInsuranceFeeFlag.equals(0) && driveProtectFeeFlag.equals(0)) {
                packageCode = InsurancePackageEnum.WHIHOUT_BUY.getIndex();
            }

            if (exclusiveDeductibleFlag.equals(1) && millionInsuranceFeeFlag.equals(0) && driveProtectFeeFlag.equals(0)) {
                packageCode = InsurancePackageEnum.EXCLUSIVE_DEDUCTIBLE_BUY.getIndex();
            }

            if (exclusiveDeductibleFlag.equals(1) && millionInsuranceFeeFlag.equals(1)) {
                packageCode = InsurancePackageEnum.EXCLUSIVE_DEDUCTIBLE_AND_MILLION_INSURANCE_BUY.getIndex();
            }

            if (exclusiveDeductibleFlag.equals(1) && driveProtectFeeFlag.equals(1)) {
                packageCode = InsurancePackageEnum.EXCLUSIVE_DEDUCTIBLE_AND_DRIVE_PROTECT_BUY.getIndex();
            }
            return packageCode;
        }
    }

    
    private static BigDecimal getInsurancePackageBeforeEnterPrice(BigDecimal exclusiveFeeBeforeEnterPrice, BigDecimal millionInsuranceFeeBeforeEnterPrice, BigDecimal driveProtectFeeBeforeEnterPrice) {
        BigDecimal totalBeforeEnterPrice = BigDecimal.ZERO;
        if (exclusiveFeeBeforeEnterPrice != null) {
            totalBeforeEnterPrice = totalBeforeEnterPrice.add(exclusiveFeeBeforeEnterPrice);
        }
        if (millionInsuranceFeeBeforeEnterPrice != null) {
            totalBeforeEnterPrice = totalBeforeEnterPrice.add(millionInsuranceFeeBeforeEnterPrice);
        }
        if (driveProtectFeeBeforeEnterPrice != null) {
            totalBeforeEnterPrice = totalBeforeEnterPrice.add(driveProtectFeeBeforeEnterPrice);
        }
        return totalBeforeEnterPrice;
    }

    /**
     * 需要排除的增值服务收费项目
     * 1.未购买全部排除
     * 2.只购买尊享服务 排除三者百万和驾乘守护收费项目
     * 3.购买尊享百万服务  排除驾乘守护收费项目
     * 4.购买尊享驾乘服务  排除三者百万收费项目
     * 5.全部购买 不排除
     * @param insurancePackageCode  购买增值服务费代码
     * @param chargeItemCode   收费项目编码
     * @return
     */
    public static boolean excludeInsurancePackage(Integer insurancePackageCode, String chargeItemCode) {
        if (getInsuranceFilter(chargeItemCode)) {
            // 未购买增值服务
            if (Integer.valueOf(InsurancePackageEnum.WHIHOUT_BUY.getIndex()).equals(insurancePackageCode)) {
                return true;
            }
            // 购买尊享服务费 排除三者百万、三者二百万和驾乘守护收费项目
            if (Integer.valueOf(InsurancePackageEnum.EXCLUSIVE_DEDUCTIBLE_BUY.getIndex()).equals(insurancePackageCode)
                    && (ChargeItemEnum.MILLION_INSURANCE_FEE.getCode().equals(chargeItemCode)
                    || ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode().equals(chargeItemCode)
                    || ChargeItemEnum.DRIVE_PROTECT_FEE.getCode().equals(chargeItemCode))) {
                return true;
            }
            // 购买尊享百万 排除三者二百万和驾乘守护收费项目
            if (Integer.valueOf(InsurancePackageEnum.EXCLUSIVE_DEDUCTIBLE_AND_MILLION_INSURANCE_BUY.getIndex()).equals(insurancePackageCode)
                    && (ChargeItemEnum.DRIVE_PROTECT_FEE.getCode().equals(chargeItemCode)
                    || ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode().equals(chargeItemCode))) {
                return true;
            }
            // 购买尊享驾乘 排除三者百万和三者二百万收费项目
            if (Integer.valueOf(InsurancePackageEnum.EXCLUSIVE_DEDUCTIBLE_AND_DRIVE_PROTECT_BUY.getIndex()).equals(insurancePackageCode)
                    && (ChargeItemEnum.MILLION_INSURANCE_FEE.getCode().equals(chargeItemCode)
                    || ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode().equals(chargeItemCode))) {
                return true;
            }
            // 购买全程无忧 排除三者二百万收费项目
            if (Integer.valueOf(InsurancePackageEnum.ALL_BUY.getIndex()).equals(insurancePackageCode)
                    && ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode().equals(chargeItemCode)) {
                return true;
            }
            // 购买全程无忧升级版 排除三者一百万收费项目
            if (Integer.valueOf(InsurancePackageEnum.ALL_PLUS_BUY.getIndex()).equals(insurancePackageCode)
                    && ChargeItemEnum.MILLION_INSURANCE_FEE.getCode().equals(chargeItemCode)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 保险套餐详情-判断某一收费项目是否存在
     * @Author yn.zhang09
     * @Date 14:27 2023/4/4
     * @param insurancePackageDetailList
     * @param chargeItemCode
     * @return java.lang.Integer
     */
    public static Integer isExistInsurancePackageDetailList(List<String> insurancePackageDetailList, String chargeItemCode) {
        if (insurancePackageDetailList == null || insurancePackageDetailList.size() == 0) {
            return 0;
        }
        return insurancePackageDetailList.contains(chargeItemCode) ? 1: 0;
    }

    /**
     * 保险套餐-判断三者一百万是否存在
     * @Author yn.zhang09
     * @Date 16:47 2023/4/10
     * @param insurancePackageDetailList
     * @return java.lang.Integer
     */
    public static Integer isExistMillionInsuranceFeeFlag(List<String> insurancePackageDetailList) {
        return isExistInsurancePackageDetailList(insurancePackageDetailList, ChargeItemEnum.MILLION_INSURANCE_FEE.getCode());
    }

    /**
     * 保险套餐-判断三者二百万是否存在
     * @Author yn.zhang09
     * @Date 16:47 2023/4/10
     * @param insurancePackageDetailList
     * @return java.lang.Integer
     */
    public static Integer isExistTwoMillionInsuranceFeeFlag(List<String> insurancePackageDetailList) {
        return isExistInsurancePackageDetailList(insurancePackageDetailList, ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode());
    }

    /**
     * 保险套餐-判断尊享服务是否存在
     * @Author yn.zhang09
     * @Date 16:47 2023/4/10
     * @param insurancePackageDetailList
     * @return java.lang.Integer
     */
    public static Integer isExistExclusiveDeductibleFlag(List<String> insurancePackageDetailList) {
        return isExistInsurancePackageDetailList(insurancePackageDetailList, ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode());
    }

    /**
     * 保险套餐-判断驾乘守护服务是否存在
     * @Author yn.zhang09
     * @Date 16:47 2023/4/10
     * @param insurancePackageDetailList
     * @return java.lang.Integer
     */
    public static Integer isExistDriveProtectFeeFlag(List<String> insurancePackageDetailList) {
        return isExistInsurancePackageDetailList(insurancePackageDetailList, ChargeItemEnum.DRIVE_PROTECT_FEE.getCode());
    }
    
    public static Integer getInsurancePackageNumber(List<String> insurancePackageDetailList) {
        Integer insurancePackageNumber = 0;
        if (insurancePackageDetailList != null && insurancePackageDetailList.size() > 0) {
            for (String insurancePackage : insurancePackageDetailList) {
                if (ChargeItemEnum.EXCLUSIVE_DEDUCTIBLE_FEE.getCode().equals(insurancePackage)) {
                    insurancePackageNumber |= EXCLUSIVE_DEDUCTIBLE_FEE_NUMBER;
                } else if (ChargeItemEnum.MILLION_INSURANCE_FEE.getCode().equals(insurancePackage)) {
                    insurancePackageNumber |= MILLION_INSURANCE_FEE_NUMBER;
                } else if (ChargeItemEnum.TWO_MILLION_INSURANCE_FEE.getCode().equals(insurancePackage)) {
                    insurancePackageNumber |= TWO_MILLION_INSURANCE_FEE_NUMBER;
                } else if (ChargeItemEnum.DRIVE_PROTECT_FEE.getCode().equals(insurancePackage)) {
                    insurancePackageNumber |= DRIVE_PROTECT_FEE_NUMBER;
                }
            }
        }
        return insurancePackageNumber;
    }
    
    /**
     * 需要排除的增值服务收费项目
     * 1.未购买全部排除
     * 2.只购买尊享服务 排除三者百万和驾乘守护收费项目
     * 3.购买尊享百万服务  排除驾乘守护收费项目
     * 4.购买尊享驾乘服务  排除三者百万收费项目
     * 5.全部购买 不排除
     * @param insurancePackageBit  购买增值服务费代码
     * @param chargeItemCode   收费项目编码
     * @return
     */
    public static boolean excludeInsurancePackageByBit(Integer insurancePackageBit, String chargeItemCode) {
        Integer bit = insurancePackageBitMap.get(chargeItemCode);
        if (bit != null) {
            return (insurancePackageBit & bit) == 0;
        }
        return false;
    }
    
    public static boolean isUnByInsurancePackage(String itemCode, Integer insurancePackageBit) {
        Integer bit = insurancePackageBitMap.get(itemCode);
        return bit != null && (insurancePackageBit & bit) == 0;
    }
    
    public static boolean isByInsurancePackage(String itemCode, Integer insurancePackageBit) {
        Integer bit = insurancePackageBitMap.get(itemCode);
        return bit != null && (insurancePackageBit & bit) != 0;
    }
    
    public static boolean isInsurancePackage(String itemCode) {
        return insurancePackageBitMap.containsKey(itemCode);
    }
    
    public static void main(String[] args) {
        int insurancePackageBit = EXCLUSIVE_DEDUCTIBLE_FEE_NUMBER | MILLION_INSURANCE_FEE_NUMBER
                | TWO_MILLION_INSURANCE_FEE_NUMBER | DRIVE_PROTECT_FEE_NUMBER
                | DRIVER_PROTECT_SERVICE_FEE_NUMBER | ATTRACT_USER_SERVICE_FEE_NUMBER;
        System.out.println(insurancePackageBit);
        System.out.println(Integer.toBinaryString(insurancePackageBit));
        int a = -1;
        int b = (1 << 31) - 1;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(b);
    }
    
}
