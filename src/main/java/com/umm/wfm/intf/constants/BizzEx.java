package com.umm.wfm.intf.constants;

/**
 * <Description> 异常码<br>
 * 第一位数字 标识 大交通，暂定使用 7<br>
 * 第二位数字 标识 业务线，机票（0）/用车（1）/汽车票（2）/火车票（3）<br>
 * 第三到四位数字 标识 系统/模块 <br>
 * 第五到八位数字 标识 异常码本身<br>
 * </Description>
 * Created by zhangff on 2016/2/20.
 */
public class BizzEx {

    /**
     * 工具类不可实例化
     */
    private BizzEx() {
    }

    /**
     * 成功
     */
    public static final int SUCCESS = 100000;


    /**
     * 失败统一CODE
     */
    public static final int FAILURE = 100001;

    /**
     * 入参校验
     */
    public static final int VERIFY_INPUT_ERROR = 101001;

    /**
     * 通用异常
     */
    public static final int COMM_EX_WRAP = 101002;

    /**
     * 反馈异常
     */
    public static final int FEED_BACK_ERROR = 101003;

    /**
     * 特殊返回封装异常
     */
    public static final int API_RESP_WRAP = 101004;

    public static final int INTERRUPTED = 101005;

    // 序列化失败
    public static final int PROTOBUF_EX = 101007;

    public static final int HYSTRIX_TIMEOUT = 101801;
    public static final int HYSTRIX_REJECTED = 101802;
    public static final int HYSTRIX_SHORTCIRCUIT = 101803;

    // 接口请求，返回失败信息
    public static final int REST_INTF_RESULT_ERROR = 101501;
    // 接口请求，result = null
    public static final int REST_INTF_RESULT_NONE = 101502;
    // 接口请求，http status != 200
    public static final int REST_INTF_HTTP_CODE_EX = 101503;
    // 接口请求，异常
    public static final int REST_INTF_EX = 101504;
    // 转换接口对象
    public static final int REST_TO_REQ = 101505;

    public static final int REST_HYSTRIX = 101506;

    public static final int TSP_INTF_EX = 101601;

    public static final int TSP_INTF_ERROR = 101602;

    public static final int TSP_INTF_REQ_ERROR = 101603;

    public static final int TSP_INTF_RESULT_NONE = 101604;

}
