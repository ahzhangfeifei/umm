package com.umm.wfm.intf.entity;

import java.util.Date;

/**
 * Created by makun on 2017/6/16.
 */
public class IdentityInfoEntity {
    private Integer id;

    private Long memberId;

    private String name;

    private String idNumber;

    private String birthday;

    private String gender;

    private String address;

    private String startDate;

    private String endDate;

    private String nation;

    private String imageFrontUrl;

    private String imageBackUrl;

    private String imageLiveUrl;

    private Date addTime;

    private Date updateTime;

    private Integer delFlag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getImageFrontUrl() {
        return imageFrontUrl;
    }

    public void setImageFrontUrl(String imageFrontUrl) {
        this.imageFrontUrl = imageFrontUrl;
    }

    public String getImageBackUrl() {
        return imageBackUrl;
    }

    public void setImageBackUrl(String imageBackUrl) {
        this.imageBackUrl = imageBackUrl;
    }

    public String getImageLiveUrl() {
        return imageLiveUrl;
    }

    public void setImageLiveUrl(String imageLiveUrl) {
        this.imageLiveUrl = imageLiveUrl;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
