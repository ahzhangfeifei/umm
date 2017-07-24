package com.umm.wfm.service.res;

import com.umm.wfm.intf.entity.WfmEmailAddressEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: lizhimu
 * Date: 2017/6/20
 * Time: 15:06
 * Desc:
 */
public interface IEmailAddressService {

    /***
     *  删除地址信息
     * @param emailAddressEntity 待删除的地址信息
     * @return 是否删除成功>0 删除成功
     */
    int delEmailAddress(WfmEmailAddressEntity emailAddressEntity);

    /***
     * 更新地址信息
     * @param emailAddressEntity 待更新地址信息
     * @return    更新是否成功 >0 成功
     */
    int updateEmailAddress(WfmEmailAddressEntity emailAddressEntity);

    /***
     * 新增地址信息
     * @param emailAddressEntity 待入库的地址信息
     * @return 地址表主键Id
     */
    int addEmailAddress(WfmEmailAddressEntity emailAddressEntity);

    /***
     * 根据主键id查询邮寄信息
     * @param id 表主键id
     * @return 对应的主键
     */
    WfmEmailAddressEntity queryById(Integer id);

    /***
     *  依据查询条件
     * @param emailAddressEntity  查询条件entity类
     * @return 邮寄地址信息列表
     */
    List<WfmEmailAddressEntity> queryBySelective(WfmEmailAddressEntity emailAddressEntity);
}
