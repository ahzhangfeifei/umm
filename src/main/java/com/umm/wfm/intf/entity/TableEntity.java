package com.umm.wfm.intf.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "table_entity")
public class TableEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 出发城市三字码
     */
    @Column(name = "org_city_iata_code")
    private String orgCityIataCode;

    /**
     * 到达城市三字码
     */
    @Column(name = "dst_city_iata_code")
    private String dstCityIataCode;

    /**
     * 航司二字码
     */
    private String airline;

    /**
     * 航班号
     */
    @Column(name = "flight_no")
    private String flightNo;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    public TableEntity(Integer id, String orgCityIataCode, String dstCityIataCode, String airline, String flightNo, Date addTime) {
        this.id = id;
        this.orgCityIataCode = orgCityIataCode;
        this.dstCityIataCode = dstCityIataCode;
        this.airline = airline;
        this.flightNo = flightNo;
        this.addTime = addTime;
    }

    public TableEntity() {
        super();
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取出发城市三字码
     *
     * @return org_city_iata_code - 出发城市三字码
     */
    public String getOrgCityIataCode() {
        return orgCityIataCode;
    }

    /**
     * 设置出发城市三字码
     *
     * @param orgCityIataCode 出发城市三字码
     */
    public void setOrgCityIataCode(String orgCityIataCode) {
        this.orgCityIataCode = orgCityIataCode == null ? null : orgCityIataCode.trim();
    }

    /**
     * 获取到达城市三字码
     *
     * @return dst_city_iata_code - 到达城市三字码
     */
    public String getDstCityIataCode() {
        return dstCityIataCode;
    }

    /**
     * 设置到达城市三字码
     *
     * @param dstCityIataCode 到达城市三字码
     */
    public void setDstCityIataCode(String dstCityIataCode) {
        this.dstCityIataCode = dstCityIataCode == null ? null : dstCityIataCode.trim();
    }

    /**
     * 获取航司二字码
     *
     * @return airline - 航司二字码
     */
    public String getAirline() {
        return airline;
    }

    /**
     * 设置航司二字码
     *
     * @param airline 航司二字码
     */
    public void setAirline(String airline) {
        this.airline = airline == null ? null : airline.trim();
    }

    /**
     * 获取航班号
     *
     * @return flight_no - 航班号
     */
    public String getFlightNo() {
        return flightNo;
    }

    /**
     * 设置航班号
     *
     * @param flightNo 航班号
     */
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo == null ? null : flightNo.trim();
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}