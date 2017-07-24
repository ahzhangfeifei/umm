package com.umm.wfm.service.res;

import com.umm.wfm.intf.tsp.dto.res.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公用的资源信息服务
 *
 * @author zhangff
 */
@Service
public class SpringBaseCacheService {

    @Resource
    TspResInterface tspResInterface;

    // ------- 机场信息缓存
    @Cacheable(value = "oneDay_airport", key = "#airportCode")
    public ResAirport getAirport(String airportCode) {
        ResRowsResp<ResAirport> resp = tspResInterface.findAirport(new ResAirPortReq().setAirportCode(airportCode));
        if (resp != null && CollectionUtils.isNotEmpty(resp.getRows())) {
            return resp.getRows().get(0);
        }
        return new ResAirport();
    }
    @CacheEvict(value = "oneDay_airport", key = "#airportCode")
    public void clearAirport(String airportCode) {
        // Do nothing because of deal with aop.
    }
    @CacheEvict(value = "oneDay_airport", allEntries = true)
    public void clearAirportAll() {
        // Do nothing because of deal with aop.
    }

    // ------- 地区信息缓存
    @Cacheable(value = "oneDay_district", key = "'district_map'")
    public Map<String, ResDistrict> getDistrictMap() {
        List<ResDistrict> districts = tspResInterface.findDistrict(new ResDistrictReq());
        Map<String, ResDistrict> districtMap = new HashMap<>();
        districts.forEach(n -> districtMap.put(n.getIataCode(), n));
        return districtMap;
    }
    @CacheEvict(value = "oneDay_district", key = "'district_map'")
    public void clearDistrictMap() {
        // Do nothing because of deal with aop.
    }

    // ------- 地区下机场信息缓存
    @Cacheable(value = "oneDay_traffices", key="#cityCode")
    public List<ResTrafficesAirport> getAirports(String cityCode) {
        ResTrafficesReq req = new ResTrafficesReq();
        req.setLikeCode(cityCode);
        List<ResTraffices> result = tspResInterface.queryTraffics(req);
        if (CollectionUtils.isNotEmpty(result)) {
            ResTraffices resTraffices = result.get(0);
            List<ResTrafficesAirport> list = resTraffices.getAirportList();
            if (CollectionUtils.isNotEmpty(list)) {
                return list;
            }
        }
        return new ArrayList<>();
    }

    @CacheEvict(value = "oneDay_traffices", allEntries = true)
    public void clearAirports() {
        // Do nothing because of deal with aop.
    }

    // -------- 航司缓存
    @Cacheable(value = "oneDay_airline_company", key = "#code")
    public ResAirlineCompany getAirlineCompany(String code) {
        ResRowsResp<ResAirlineCompany> resp = tspResInterface
                .findAirlineCompany(new ResAirlineCompanyReq().setAirlineCompanyIataCode(code));
        if (resp != null && CollectionUtils.isNotEmpty(resp.getRows())) {
            return resp.getRows().get(0);
        }
        return new ResAirlineCompany();
    }
    @CacheEvict(value = "oneDay_airline_company", allEntries = true)
    public void clearAirlineCompany() {
        // Do nothing because of deal with aop.
    }

}
