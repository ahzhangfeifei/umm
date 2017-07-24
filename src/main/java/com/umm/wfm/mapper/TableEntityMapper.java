package com.umm.wfm.mapper;

import com.umm.wfm.intf.entity.TableEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TableEntityMapper extends Mapper<TableEntity> {

//    /**
//     * 线路表，不存在的记录则插入，基于unique键
//     * @param wfmRoute 线路对象
//     * @return 影响行数
//     */
//    int saveRoute(AvdRoute wfmRoute);
//
//    /**
//     * 根据航司二字码查询线路
//     * @param airline 航司二字码
//     * @return 线路对象列表 (BJS-NKG)
//     */
//    List<String> queryCityByAirline(String airline);
//
//    /**
//     * 根据线路查询航司二字码
//     * @param org 出发城市
//     * @param dst 目的城市
//     * @return 航司列表
//     */
//    List<String> queryAirlineByCity(@Param("org") String org, @Param("dst") String dst);
//
//    // 找到已无引用的线路
//    List<AvdRoute> findNoUseRoute(int countLimit);
//
//    // 删除已无引用的线路
//    int deleteBatch(List<AvdRoute> routes);

}