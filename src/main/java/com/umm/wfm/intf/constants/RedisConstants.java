package com.umm.wfm.intf.constants;

/**
 * Redis相关常量
 *
 * @author zhangff
 */
public class RedisConstants {

    /**
     * 1分钟（毫秒单位）
     */
    public static final int MIN_IN_MILL = 60 * 1000;

    /**
     * 5分钟
     */
    public static final int FIVE_IN_MIN = 5 * 60;


    /**
     * 10分钟
     */
    public static final int TEN_IN_MIN = 10 * 60;

    /**
     * 10秒（毫秒单位）
     */
    public static final int SEC_10_IN_MILL = 10 * 1000;

    /**
     * 一天的时间
     */
    public static final int DAYTIME = 24 * 60 * 60;

    /**
     * 三天的时间
     */
    public static final int DAY_3 = 3 * 24 * 60 * 60;
    /**
     * 一周的时间
     */
    public static final int WEEKTIME = 7 * 24 * 60 * 60;

    public static final int HALF_MONTH_TIME = 15 * 24 * 60 * 60;

    /**
     * 3个小时
     */
    public static final int HOUR_3 = 3 * 60 * 60;

    /**
     * 1个小时
     */
    public static final int HOUR_1 = 1 * 60 * 60;
    /**
     * 2个小时
     */
    public static final int HOUR_2 = 2 * 60 * 60;

    public static final int MONTH_IN_MINUTE = 30 * 24 * 60;
    /**
     * 20年的时间
     */
    public static final int YEARTIME = 20 * 360 * 24 * 60 * 60;

    /**
     * 1年的时间(秒)
     */
    public static final int ONE_YEAR = 366 * 24 * 60 * 60;


    /**
     * 通用过期时间
     */
    public static final int COMMON_EXPIRE_TIME = 60 * 60;

    /**
     * 一个月时间
     */
    public static final int MONTHTIME = 30 * 24 * 60 * 60;

//
//    /**
//     * 缓存命中topn统计
//     */
//    public static final String AV_STATICS_HITS_TOPN_KEY = "wfm:av:statics:hits:topn";
//
//    /**
//     * AV DATA KEY
//     */
//    public static final String AV_DATA_KEY = "wfm:av:main:";


    private int version;

    public RedisConstants() {
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
