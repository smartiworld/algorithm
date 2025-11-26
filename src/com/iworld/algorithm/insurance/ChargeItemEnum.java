package com.iworld.algorithm.insurance;

public enum ChargeItemEnum {
    
    /**
     * 各收费项目
     */
    VEHICLE_RENT_FEE(1, "100000001", "车辆租赁及服务费"),
    BASIC_SERVICE_FEE(2, "100000002", "基础服务费"),
    VEHICLE_SERVICING_FEE(3, "100000003", "车辆整备费"),
    EXCLUSIVE_DEDUCTIBLE_FEE(4, "100000004", "尊享服务费"),
    DIFF_CITY_FEE(5, "100000006", "异地还车费"),
    CROSS_POINT_FEE(6, "100000005", "跨网点还车费"),
    DOOR_DELIVER_FEE(7, "100000007", "上门(取车)服务费"),
    DOOR_RETRIEVE_FEE(8, "100000008", "上门(还车)服务费"),
    MODIFY_PENALTY_FEE(9, "100000011", "修改违约金"),
    CHANGE_PENALTY_FEE(10, "100000012", "换车违约金"),
    CANCEL_PENALTY_FEE(11, "100000021", "取消违约金"),
    EARLY_RETURN_PENALTY_FEE(12, "100000013", "提前还车违约金"),
    OIL_SERVICE_FEE(13, "100000009", "加油服务费"),
    OIL_FEE(14, "100000010", "油费"),
    PARKING(101, "100000014", "智慧停车费"),
    PURCHASE_EXEMPTION(999998, "100000023", "宝沃购车减免"),
    PECCANCY_FINE(16, "100000018", "违章罚款"),
    PECCANCY_AGENTFEE(17, "100000019", "违章违约金"),
    ETC_UCARINC(18, "100000015", "ETC通行费-神州"),
    ETC_TICKET(19, "100000016", "ETC通行费"),
    ETC_PARK_FEE(20, "100000017", "ETC停车费"),
    REPAIR(105, "100000020", "车辆损失费"),
    OVERTIME_SERVICE_FEE(21, "100000024", "小时费"),
    MILLION_INSURANCE_FEE(22, "100000025", "三者一百万服务费"),
    DRIVE_PROTECT_FEE(23, "100000026", "驾乘守护服务费"),
    ELECTRIC_FEE(24, "100000027", "电费"),
    CHARGING_SERVICE(25, "100000028", "充电服务费"),
    ETC_SERVICE(26, "100000029", "ETC服务费"),
    TWO_MILLION_INSURANCE_FEE(27, "100000030", "三者二百万服务费"),
    UPGRADE_SERVICE_FEE(28, "100000031", "升级服务费"),
    CHILD_SEATS_SERVICE_FEE(29, "100000032", "儿童座椅服务费"),
    DRIVER_PROTECT_SERVICE_FEE(30, "100000033", "驾驶员守护服务费"),
    ATTRACT_USER_SERVICE_FEE(31, "100000034", "引流服务费"),
    AUTO_DRIVE_RENT_FEE(32, "100000035", "智驾车辆租赁费"),
    AUTO_DRIVE_HOURLY_FEE(33, "100000036", "零散小时费"),
    PASS_THROUGH_SERVICE_FEE(34, "100000037", "通行服务费"),
    THREE_HUNDRED_THOUSAND_INSURANCE_FEE(35, "100000038", "三者三十万服务费"),
    ONE_AND_A_HALF_MILLION_INSURANCE_FEE(36, "100000039", "三者一百五十服务费"),
    THREE_MILLION_INSURANCE_FEE(37, "100000040", "三者三百万服务费"),
    TWO_HUNDRED_THOUSAND_DRIVER_PROTECT_SERVICE_FEE(38, "100000041", "驾驶员守护二十万服务费"),
    HALF_A_MILLION_DRIVER_PROTECT_SERVICE_FEE(39, "100000042", "驾驶员守护五十万服务费"),
    DRIVE_PROTECT_10_FEE(40, "100000043", "驾乘守护十万服务费"),
    DRIVE_PROTECT_20_FEE(41, "100000044", "驾乘守护二十万服务费"),
    DRIVE_PROTECT_50_FEE(42, "100000045", "驾乘守护五十万服务费"),
    OUTSIDE_MEDICAL_INSURANCE_10_FEE(43, "100000046", "三者医保外用药十万服务费"),
    TRAVEL_BAG(44, "100000047", "随车出行包"),
    OTHER(999999, "100000022", "其他"),
    ;

    private int index;
    private String code;
    private String name;

    private ChargeItemEnum(int index, String code, String name){
        this.index = index;
        this.code = code;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByIndex(int index){
        for(ChargeItemEnum item: ChargeItemEnum.values()){
            if(item.getIndex() == index){
                return item.name;
            }
        }
        return null;
    }

    public static ChargeItemEnum getEnumByIndex(int index){
        for(ChargeItemEnum item : ChargeItemEnum.values()){
            if(item.getIndex() == index){
                return item;
            }
        }
        return null;
    }

    public static String getNameByCode(String code){
        for(ChargeItemEnum item : ChargeItemEnum.values()){
            if(item.getCode().equals(code)){
                return item.name;
            }
        }
        return null;
    }

    public static ChargeItemEnum getEnumByCode(String code){
        for(ChargeItemEnum item : ChargeItemEnum.values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }

}
