package com.iworld.algorithm.distance.pojo;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/4/8 11:24
 */
public class Result<T> {
    
    /**
     * 成功.
     */
    public final static int REMOTE_SUCCES_STATUS = 0;
    
    /**
     * 业务异常.
     */
    public final static int REMOTE_BUSINESS_ERROR_STATUS = -1;
    
    /**
     * 服务异常.
     */
    public final static int REMOTE_SERVICE_ERROR_STATUS = -2;
    
    /**
     * 服务状态码. 0 为成功，-1为业务异常，-2为服务异常。
     */
    private int status = REMOTE_SUCCES_STATUS;
    
    /**
     * 描述信息。用于返回业务异常信息和服务异常信息，不可用于设置日志堆栈信息，和code取其一设值即可。举例：业务信息： msg = "附近暂无司机" ，服务异常信息：msg="订单创建失败".
     */
    private String msg;
    
    /**
     * 业务语义状态码，用于做业务语义路由，和msg取其一设值即可
     */
    private String code;
    
    /**
     * 业务正常返回的消息体,每个服务使用专用Re对象，不可复用。属性命名规则同数据库命名。支持数据结构包装，例如：List<Re>，Set<Re>。
     */
    private T re;
    
    public Result() {
    }
    
    /**
     * 得到成功状态结果.
     * @param re 正常结果
     * @return
     */
    public static <T> Result<T> getSuccessResult(T re) {
        Result<T> lbsResult = new Result<T>();
        lbsResult.setStatus(REMOTE_SUCCES_STATUS);
        lbsResult.setRe(re);
        return lbsResult;
    }
    
    /**
     * 得到业务异常Result.
     * @param msg  用于设置Result.msg字段
     * @param code 用于设置Result.code字段
     */
    public static Result<Object> getBusinessException(String msg, String code) {
        Result<Object> lbsResult = new Result<Object>();
        lbsResult.setStatus(REMOTE_BUSINESS_ERROR_STATUS);
        lbsResult.setMsg(msg);
        lbsResult.setCode(code);
        return lbsResult;
    }
    
    /**
     * 得到服务异常Result.
     * @param msg 用于设置Result.msg字段
     * @param code    用于设置Result.code字段
     */
    public static Result<Object> getServiceError(String msg, String code) {
        Result<Object> lbsResult = new Result<Object>();
        lbsResult.setStatus(REMOTE_SERVICE_ERROR_STATUS);
        lbsResult.setMsg(msg);
        lbsResult.setCode(code);
        return lbsResult;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public T getRe() {
        return re;
    }
    
    public void setRe(T re) {
        this.re = re;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
}
