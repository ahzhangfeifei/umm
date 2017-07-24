package com.umm.wfm.service;

import com.umm.wfm.intf.dto.member.LoginReqDto;
import com.umm.wfm.intf.entity.WfmLoginLogEntity;

/**
 * Created by makun on 2017/6/15.
 */
public interface ILoginLogInfoService {
    /**
     * 保存会员登录信息
     *
     * @param loginReqDto 请求入参
     * @param memberId    会员Id
     * @param operateType 操作类型 参数
     */
    void saveLoginLog(LoginReqDto loginReqDto, long memberId, int operateType);


    /**
     * 查询上次登录信息
     *
     * @param memberId
     * @return
     */
    WfmLoginLogEntity getLastLoginLog(Long memberId);
}
