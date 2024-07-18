package com.iworld.algorithm.distance.enums;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 11:37
 */
public enum DrivingStrategyEnum {
    
    /**
     * 高德计算策略
     *  // 0速度优先（时间）
     //1费用优先（不走收费路段的最快道路）
     //2距离优先
     //3不走快速路
     //4躲避拥堵
     //5多策略（同时使用速度优先、费用优先、距离优先三个策略计算路径）。
     //其中必须说明，就算使用三个策略算路，会根据路况不固定的返回一~三条路径规划信息。
     //6不走高速
     //7不走高速且避免收费
     //8躲避收费和拥堵
     //9不走高速且躲避收费和拥堵
     *
     */
    
    /**
     * 百度计算策略 不能严格对应，设置相似对应值
     *
     *http://api.map.baidu.com/routematrix/v2/driving?  //GET请求
     * https://api.map.baidu.com/routematrix/v2/driving?  //GET请求
     * 算路偏好，该参数只对驾车算路(driving)生效。 该服务为满足性能需求，不含道路阻断信息干预。
     * 可选值：
     * 10： 不走高速；
     *
     * 11：常规路线，即多数用户常走的一条经验路线，满足大多数场景需求，是较推荐的一个策略
     *
     * 12： 距离较短（考虑路况）：即距离相对较短的一条路线，但并不一定是一条优质路线。计算耗时时，考虑路况对耗时的影响；
     *
     * 13： 距离较短（不考虑路况）：路线同以上，但计算耗时时，不考虑路况对耗时的影响，可理解为在路况完全通畅时预计耗时。
     * 注：除13外，其他偏好的耗时计算都考虑实时路况
     */
    
    /*(策略索引,高德策略,百度策略,策略说明)*/
    SPEED_FIRST_0(0,0,12,"0速度优先（时间）"),
    COST_FRIST_1(1,1,12,"1费用优先（不走收费路段的最快道路） "),
    DISTANCE_FRIST_2(2,2, 13,"2距离优先 "),
    NO_EXPRESS_HWAY_3(3,3,12,"3不走快速路 "),
    AVOID_CRUSH_4(4,4,12,"4躲避拥堵 "),
    /**
     * //其中必须说明，就算使用三个策略计算路径距离，也是根据路况不固定的返回一~三条路径规划信息。
     */
    MULTI_STRATEGY_5(5,5,12,"5多策略（同时使用速度优先、费用优先、距离优先三个策略计算路径）"),
    NO_HIGHWAY_6(6,6,10, "6不走高速"),
    NO_HIGHWAY_NO_COST_7(7,7,12,"7不走高速且避免收费 "),
    NO_COST_AVOID_CRUSH_8(8,8,12,"8躲避收费和拥堵"),
    NO_HIGHWAY_NO_COST_NO_CRUSH_9(9,9,12,"9不走高速且躲避收费和拥堵");
    
    
    DrivingStrategyEnum(int v ,int gv , int bv, String d){
        val = v;
        golderVal = gv;
        baiduVal = bv;
        desc = d;
    }
    
    private int val;
    
    private int golderVal;
    
    private int baiduVal;
    
    private String desc;
    
    public int getVal() {
        return val;
    }
    
    public int getGolderVal() {
        return golderVal;
    }
    
    public int getBaiduVal() {
        return baiduVal;
    }
    
    public String getDesc() {
        return desc;
    }
    
    
    public  static  DrivingStrategyEnum getStrategyByVal( Integer val){
        if (val == null) {
            
            return null;
        }
        
        for (DrivingStrategyEnum strategyEnum : DrivingStrategyEnum.values()) {
            if (strategyEnum.getVal() == val.intValue()) {
                return strategyEnum;
            }
        }
        return null;
    }
}
