package com.umm.wfm.intf.dto.support;

/**
 * 反刷转AV配置项目
 * Created by zhangff on 2016/8/6.
 */
public class RaeConf {

    /**
     * 出发三字码
     */
    private String org;

    /**
     * 到达三字码
     */
    private String dst;

    /**
     * 团期据当天天数范围（开始），在范围内反刷转实时AV
     */
    private int rangeStart = 0;

    /**
     * 团期据当天天数范围（结束），在范围内反刷转实时AV
     */
    private int rangeEnd = 20;

    /**
     * 失效超过分钟数限制，高于此值，反刷转实时AV
     */
    private int expireLimit = 5;

    /**
     * 余位限制，低于此值，反刷转实时AV
     */
    private int seatLimit = 3;

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public int getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(int rangeStart) {
        this.rangeStart = rangeStart;
    }

    public int getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(int rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    public int getExpireLimit() {
        return expireLimit;
    }

    public void setExpireLimit(int expireLimit) {
        this.expireLimit = expireLimit;
    }

    public int getSeatLimit() {
        return seatLimit;
    }

    public void setSeatLimit(int seatLimit) {
        this.seatLimit = seatLimit;
    }
}
