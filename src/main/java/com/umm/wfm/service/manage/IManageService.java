package com.umm.wfm.service.manage;

import com.umm.wfm.intf.entity.MemberInfo;

import java.util.List;

/**
 * Created by makun on 2017/6/13.
 * 管理员 服务类
 */

public interface IManageService {

    /**
     * @param offset 分页 参数， 起始位置
     * @param limit  分页 参数， 单页数目
     * @return 结果集
     */
    List<MemberInfo> getMemberList(int offset, int limit);

    /**
     * @param memberId 会员id 入参
     * @return 出参： 会员对象
     */
    MemberInfo getMemberById(String memberId);
}
