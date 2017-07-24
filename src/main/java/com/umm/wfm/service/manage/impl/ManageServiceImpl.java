package com.umm.wfm.service.manage.impl;

import com.umm.wfm.intf.entity.MemberInfo;
import com.umm.wfm.security.log.LogUtil;
import com.umm.wfm.service.manage.IManageService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by makun on 2017/6/13.
 * 管理员 服务类
 */

@Service
public class ManageServiceImpl implements IManageService {
    private static final Logger LOGGER = LogUtil.getLogger(ManageServiceImpl.class);

    /**
     * @param offset 分页 参数， 起始位置
     * @param limit  分页 参数， 单页数目
     * @return 结果集
     */
    public List<MemberInfo> getMemberList(int offset, int limit) {
        return null;
    }

    /**
     * @param memberId 会员id 入参
     * @return 出参： 会员对象
     */
    public MemberInfo getMemberById(String memberId) {
        return null;
    }
}
