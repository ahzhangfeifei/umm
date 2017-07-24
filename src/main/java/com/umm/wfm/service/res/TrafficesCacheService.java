package com.umm.wfm.service.res;

import com.umm.ats.core.courier.AbstractCourier;
import com.umm.wfm.intf.tsp.dto.res.ResTrafficesAirport;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 地区缓存服务
 * Created by zhangff on 2016/8/24.
 */
@Service
public class TrafficesCacheService extends AbstractCourier<String> {

    @Resource
    private SpringBaseCacheService springBaseCacheService;

    @Override
    protected void courierReceived(String message) {
        springBaseCacheService.clearAirports();
    }

    public String findOnlyAirportCode(String iataCode) {
        List<ResTrafficesAirport> list = springBaseCacheService.getAirports(iataCode);
        if (CollectionUtils.isNotEmpty(list) && list.size() == 1) {
            return list.get(0).getAirportCode();
        }
        return StringUtils.EMPTY;
    }

    public List<String> findAirportCodes(String iataCode) {
        List<ResTrafficesAirport> list = springBaseCacheService.getAirports(iataCode);
        return list.stream().map(ResTrafficesAirport::getAirportCode).collect(Collectors.toList());
    }

}
