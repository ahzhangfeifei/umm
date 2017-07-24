package com.umm.wfm.service.res;

import com.umm.wfm.intf.tsp.dto.res.ResDistrict;
import com.umm.ats.core.courier.AbstractCourier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 地区缓存服务
 * Created by zhangff on 2016/8/24.
 */
@Service
public class DistrictCacheService extends AbstractCourier<String> {

    @Resource
    private SpringBaseCacheService springBaseCacheService;

    @Override
    protected void courierReceived(String message) {
        springBaseCacheService.clearDistrictMap();
    }

    public String findDistrictName(String iataCode) {
        ResDistrict resDistrict = findDistrict(iataCode);
        return resDistrict == null ? null : resDistrict.getName();
    }

    public ResDistrict findDistrict(String iataCode) {
        Map<String, ResDistrict> map = springBaseCacheService.getDistrictMap();
        return map.get(iataCode);
    }

}
