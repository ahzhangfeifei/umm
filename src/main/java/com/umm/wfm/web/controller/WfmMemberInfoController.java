package com.umm.wfm.web.controller;

import com.umm.ats.core.tsp4server.TspBody;
import com.umm.ats.core.tsp4server.TspService;
import com.umm.operation.platform.tsg.base.core.utils.ResponseVo;
import com.umm.wfm.intf.entity.AccoutType;
import com.umm.wfm.intf.entity.MemberInfo;
import com.umm.wfm.security.log.LogUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.PrintWriter;

/**
 * 小黑鱼会员信息api Created by szs on 2017/06/14.
 */
@Controller
@RequestMapping(value = "/member")
public class WfmMemberInfoController {

    private static final Logger LOGGER = LogUtil.getLogger(WfmMemberInfoController.class);

    @Resource
    private MemberInfoService memberInfoService;

    /**
     * 查询会员信息列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @TspService
    public Object query(@TspBody MemberInfo vo) {
        return memberInfoService.queryList(vo);
    }

    /**
     * 根据id查询会员信息(优先缓存)
     *
     * @return
     */
    @RequestMapping(value = "/id/cache", method = RequestMethod.GET)
    @TspService
    public Object queryFromCache(@TspBody MemberInfo vo) {
        Object res = null;
        try {
            res = memberInfoService.queryMemberFromCacheById(vo.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 根据手机号查询会员信息(优先缓存)
     *
     * @return
     */
    @RequestMapping(value = "/tel/cache", method = RequestMethod.GET)
    @TspService
    public Object queryIdFromCache(@TspBody MemberInfo vo) {
        Long id = 0L;
        try {
            id = memberInfoService.queryMemberIdFromCacheByCon(vo.getMobileTel(), AccoutType.TEL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /***
     * 运营界面会员详情接口
     *
     * @param memberId
     * @return responseVo
     */
    @RequestMapping(value = "/query/detail/{memberId}", method = RequestMethod.GET)
    @TspService
    public Object queryMemberDetailById(@PathVariable Long memberId) {
        ResponseVo responseVo = new ResponseVo();
        PrintWriter writer = null;
        try {
            responseVo.setSuccess(true);
            responseVo.setData(memberInfoService.getMemberDetail(memberId));
        } catch (Exception e) {
            LOGGER.error("/query/detail/" + memberId + "query Exception", e);
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        return responseVo;
    }
}
