package com.iworld.algorithm;

import java.math.BigDecimal;

/**
 * @author liang.xu01
 * @description 价格配置项默认值
 * @date 2019/11/9 10:09
 * @since 1.0
 */
public class DefaultPriceValueConstant {

    /**
     * 宏观门店价格默认值
     */
    public static final BigDecimal MACRO_DEPT_PRICE_DEFAULT = BigDecimal.ZERO;

    /**
     * 基础服务费价格默认值
     */
    public static final BigDecimal BASIC_SERVICE_FEE_DEFAULT = BigDecimal.valueOf(40D);

	/**
	 * 尊享服务费默认值
	 */
	public static final BigDecimal EXCLUSIVE_DEDUCTIBLE_FEE_DEFAULT = BigDecimal.valueOf(50D);

	/**
	 * 尊享服务费比例(全程无忧折扣)默认值
	 */
	public static final int EXCLUSIVE_DEDUCTIBLE_INSURANCE_DISCOUNT_DEFAULT = 90;

	/**
	 * 尊享服务费默认收取7天
	 */
	public static final Integer EXCLUSIVE_DEDUCTIBLE_FEE_COUNT_DEFAULT = 7;
	/**
	 * 三者百万费默认收取7天
	 */
	public static final Integer MILLION_INSURANCE_FEE_COUNT_DEFAULT = 7;
	/**
	 * 驾乘守护默认收取7天
	 */
	public static final Integer DRIVE_PROTECT_FEE_COUNT_DEFAULT = 7;

    /**
     * 车辆整备费价格默认值
     */
    public static final BigDecimal VEHICLE_SERVICING_FEE_DEFAULT = BigDecimal.valueOf(20D);

    /**
     * 异地还车费价格默认值
     */
    public static final BigDecimal DIFF_CITY_FEE_DEFAULT = BigDecimal.valueOf(2D);

	/**
	 * 跨网点还车默认距离
	 */
	public static final Integer CROSS_POINT_FEE_DISTANCE_DEFAULT = 3;

	/**
	 * 跨网点还车选择上门时默认门店id
	 */
	public static final Long CROSS_POINT_FEE_DEPT_DEFAULT = 0L;

	/**
	 * 跨网点第一梯度默认价格
	 */
	public static final BigDecimal CROSS_POINT_FEE_FIRST_DEFAULT = BigDecimal.ZERO;

	/**
	 * 跨网点第二梯度默认价格
	 */
	public static final BigDecimal CROSS_POINT_FEE_SECOND_DEFAULT = BigDecimal.valueOf(1.5D);

    /**
     * 上门送车费第一梯度默认截至距离
     */
    public static final Integer DOOR_DELIVER_FEE_FIRST_END_DISTANCE_DEFAULT = 1;

    /**
     * 上门送车费第一梯度默认价格
     */
    public static final BigDecimal DOOR_DELIVER_FEE_FIRST_DEFAULT = BigDecimal.ZERO;

    /**
     * 上门送车费第二梯度默认价格
     */
    public static final BigDecimal DOOR_DELIVER_FEE_SECOND_DEFAULT = BigDecimal.valueOf(7D);

    /**
     * 上门送车费第一梯度默认截至距离
     */
    public static final Integer DOOR_DELIVER_FEE_SECOND_END_DISTANCE_DEFAULT = -1;

    /**
     * 上门取车费第一梯度默认价格
     */
    public static final BigDecimal DOOR_RETRIEVE_FEE_FIRST_DEFAULT = BigDecimal.ZERO;

    /**
     * 上门取车费第二梯度默认价格
     */
    public static final BigDecimal DOOR_RETRIEVE_FEE_SECOND_DEFAULT = BigDecimal.valueOf(7D);

    /**
     * 上门取车费第一梯度默认截至距离
     */
    public static final Integer DOOR_RETRIEVE_FEE_FIRST_END_DISTANCE_DEFAULT = 1;

    /**
     * 上门取车费第二梯度默认截至距离
     */
    public static final Integer DOOR_RETRIEVE_FEE_SECOND_END_DISTANCE_DEFAULT = -1;

    /**
     * 长租期保险费用配置最终梯队默认租期值
     */
    public static final Integer LONG_RENT_INSURANCE_FEE_END_RENT_TERM_DEFAULT = -1;

	/**
	 * 微观出租率默认值
	 */
	public static final Integer MICRO_RENT_RATE_DEFAULT = 100;

	/**
	 * 微观出租率折扣默认值
	 */
	public static final Integer MICRO_DISCOUNT_DEFAULT = 100;

	/**
	 * 套餐折扣默认值
	 */
	public static final Integer PACKAGE_DISCOUNT_DEFAULT = 100;

	/**
	 * 套餐折扣起始租期默认值
	 */
	public static final Integer PACKAGE_DISCOUNT_RENTDAY_START_TERM_DEFAULT = 1;

	/**
	 * 套餐折扣结束租期默认值
	 */
	public static final Integer PACKAGE_DISCOUNT_RENTDAY_END_TERM__DEFAULT = 999999;

	/**
	 * 半日租折扣默认值
	 */
	public static final Integer HALFDAY_DISCOUNT_DEFAULT = 100;

	/**
	 * 强行续租系数默认值
	 */
	public static final Integer FORCE_RENT_DISCOUNT_DEFAULT = 100;

	/**
	 * 超时还车系数默认值
	 */
	public static final Integer OVERTIME_DISCOUNT_DEFAULT = 100;
	/**
	 * 企业折扣默认值
	 */
	public static final Integer ENTERPRISE_DISCOUNT_DEFAULT = 100;
	/**
	 * 长租强行续租系数默认值
	 */
	public static final Integer LONG_RENT_FORCE_RENT_DISCOUNT_DEFAULT = 100;
	/**
	 * 长租超时还车系数默认值
	 */
	public static final Integer LONG_RENT_OVERTIME_DISCOUNT_DEFAULT = 100;
	/**
	 * 长租超期还车系数默认值
	 */
	public static final Integer LONG_RENT_OVERDUE_DISCOUNT_DEFAULT = 100;

	/**
	 * 半日租计价默认4个小时
	 */
	@Deprecated
	public static final Integer HALFDAY_DEFAULT_HOUR = 4;

	/**
	 * 取消违约金计价类型对应的收费比例
	 */
	public static final class CANCEL_PENALTY_FEE{
		/**
		 * 日租
		 */
		public static final Integer DAY_RENT_DISCOUNT = 30;
		/**
		 * 套餐
		 */
		public static final Integer PACKAGE_DISCOUNT = 30;
		/**
		 * 顺风车
		 */
		public static final Integer HIT_RENT_DISCOUNT = 30;
		/**
		 * 节假日预付
		 */
		public static final Integer HOLIDAY_DISCOUNT = 30;

		/**
		 * 非上门日租
		 */
		public static final Integer DAY_NO_DELIVER_DISCOUNT = 30;
		/**
		 * 飞猪
		 */
		public static final Integer FEI_ZHU_DISCOUNT = 30;
	}

	/**
	 * 收取取消违约金时间阈值
	 */
	public static final class CANCEL_PENALTY_FEE_DISCOUNT_HOUR{
		/**
		 * 日租上门
		 */
		public static final Integer DAY_RENT_DISCOUNT_HOUR = 4;
		/**
		 * 日租非上门
		 */
		public static final Integer DAY_NO_DELIVER_DISCOUNT_HOUR = 2;
		/**
		 * 节假日预付
		 */
		public static final Integer HOLIDAY_DISCOUNT_HOUR = 48;
		/**
		 * 套餐
		 */
		public static final Integer PACKAGE_DISCOUNT_HOUR = 4;
		/**
		 * 顺风车
		 */
		public static final Integer HIT_RENT_DISCOUNT_HOUR = 4;
		/**
		 * 飞猪日租
		 */
		public static final Integer FEI_ZHU_DAY_RENT_DISCOUNT_HOUR = 4;
		/**
		 * 飞猪节假日
		 */
		public static final Integer FEI_ZHU_HOLIDAY_DISCOUNT_HOUR = 48;

	}

    /**
     * 长租取消违约金默认收取7天
     */
    public static final Integer LONG_RENT_CANCEL_PENALTY_FEE_COUNT_DEFAULT = 7;

	/**
	 * 判断加油服务费的默认油量百分比
	 */
	public static final Integer OIL_PERCENT_CHECKSUM_DEFAULT = 50;

	/**
	 * 加油服务费默认值
	 */
	public static final BigDecimal OIL_SERVICE_FEE_DEFAULT = BigDecimal.valueOf(100D);

	/**
	 * 提前还车违约金折扣
	 */
	public static final Integer EARLY_RETURN_FEE_DISCOUNT = 100;
    /**
     * 长租期提前还车违约金折扣
     */
	public static final Integer LONG_RENT_EARLY_RETURN_FEE_DISCOUNT_DEFAULT = 30;
    /**
     * 长租期提前还车违约金扣取天数
     */
    public static final Integer LONG_RENT_EARLY_RETURN_FEE_DAY_DEFAULT = 15;

	/**
	 * 根据订单id查询数据时，最大重试次数
	 */
	public static final Integer MAX_RETRY_NUM_DEFAULT = 3;

	/**
	 * 收取修改违约金的时间间隔
	 */
	public static final Integer DEFAULT_MODIFY_PENALTY_HOUR_DISTANT = 2;

	/**
	 * 超时服务费允许超时时间
	 */
	public static final Integer OVERTIME_SERVICE_FEE_HOUR = 4;

	/**
	 * 超时服务费默认值
	 */
	public static final BigDecimal OVERTIME_SERVICE_FEE_DEFAULT = new BigDecimal(40L);

	/**
	 * 三者百万费默认值
	 */
	public static final BigDecimal MILLION_INSURANCE_FEE_DEFAULT = BigDecimal.valueOf(50D);
	/**
	 * 驾乘守护费默认值
	 */
	public static final BigDecimal DRIVE_PROTECT_FEE_DEFAULT = BigDecimal.valueOf(30D);
	/**
	 * 三者百万费默认减免值
	 */
	public static final BigDecimal MILLION_INSURANCE_FEE_REDUCE_DEFAULT = BigDecimal.valueOf(20D);
	/**
	 * 驾乘守护费默认减免值
	 */
	public static final BigDecimal DRIVE_PROTECT_FEE_REDUCE_DEFAULT = BigDecimal.valueOf(10D);

	/**
	 * 平移天数
	 */
	public static final Integer MOVE_DAY = -1;
}
