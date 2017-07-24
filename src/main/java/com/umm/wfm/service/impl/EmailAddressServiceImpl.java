package com.umm.wfm.service.impl;

import com.umm.wfm.intf.entity.WfmEmailAddressEntity;
import com.umm.wfm.security.log.LogUtil;
import com.umm.wfm.service.res.IEmailAddressService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: lizhimu
 * Date: 2017/6/20
 * Time: 15:12
 * Desc:
 */
@Service
public class EmailAddressServiceImpl implements IEmailAddressService {

    private static final Logger LOGGER = LogUtil.getLogger(EmailAddressServiceImpl.class);

    @Resource
    private WfmEmailAddressMapper emailAddressMapper;

    @Override
    public int delEmailAddress(WfmEmailAddressEntity emailAddressEntity) {
        return emailAddressMapper.deleteById(emailAddressEntity.getId());
    }

    @Override
    public int updateEmailAddress(WfmEmailAddressEntity emailAddressEntity) {
        return emailAddressMapper.updateById(emailAddressEntity);
    }

    @Override
    public int addEmailAddress(WfmEmailAddressEntity emailAddressEntity) {
        return emailAddressMapper.insert(emailAddressEntity);
    }

    @Override
    public WfmEmailAddressEntity queryById(Integer id) {
        return emailAddressMapper.selectById(id);
    }

    @Override
    public List<WfmEmailAddressEntity> queryBySelective(WfmEmailAddressEntity emailAddressEntity) {
        return emailAddressMapper.selectByMemberId(emailAddressEntity.getMemberId());
    }
}
