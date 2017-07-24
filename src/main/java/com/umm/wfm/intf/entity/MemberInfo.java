package com.umm.wfm.intf.entity;

import com.umm.finance.base.security.service.annotation.KeyVersion;
import com.umm.finance.base.security.service.annotation.Security;
import com.umm.wfm.base.format.JackJsonDateFormat;
import com.umm.wfm.base.format.JackJsonDateParse;
import com.umm.wfm.base.format.JackJsonDateTimeFormat;
import com.umm.wfm.base.format.JackJsonDateTimeParse;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

//会员信息
@Table(name = "wfm_member_info")
public class MemberInfo {
	// 会员id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// 用户名
	@Security
	@Column(name = "user_name")
	private String userName;
	// 会员昵称
	@Column(name = "nick_name")
	private String nickName;
	// 会员头像地址
	@Column(name = "avatar_url")
	private String avatarUrl;
	// 状态
	@Column(name = "status")
	private Integer status;
	// 性别
	@Column(name = "gender")
	private String gender;
	// 指纹
	@Column(name = "finger_print")
	private String fingerPrint;
	// 登录时间
	@Column(name = "login_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date loginTime;
	// 注册时间
	@JsonSerialize(using = JackJsonDateTimeFormat.class)
	@JsonDeserialize(using = JackJsonDateTimeParse.class)
	@Column(name = "add_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date addTime;
	// 更新时间
	@JsonSerialize(using = JackJsonDateTimeFormat.class)
	@JsonDeserialize(using = JackJsonDateTimeParse.class)
	@Column(name = "update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date updateTime;
	@Column(name = "del_flag")
	private Integer delFlag;

	// 手机号码
	@Security
	@Column(name = "mobile_tel")
	private String mobileTel;
	// 密码
	@Security
	@Column(name = "password")
	private String password;
	// 邮箱
	@Security
	@Column(name = "email")
	private String email;
	// 证件类型
	@Column(name = "card_type")
	private Integer cardType;
	// 证件号码 0未知 1身份证
	@Security
	@Column(name = "id_number")
	private String idNumber;
	// 积分
	@Column(name = "integral")
	private Integer integral;
	// 会员等级
	@Column(name = "level")
	private Integer level;
	// 生日
	@Security
	@Column(name = "birthday")
	private String birthday;
	// 星座
	@Column(name = "constellatory")
	private Integer constellatory;
	// 职业(是否存编码还需确认)
	@Column(name = "occupation")
	private String occupation;
	// 国家地区(是否存编码还需确认)
	@Column(name = "nation")
	private String nation;
	// 兴趣爱好
	@Column(name = "interest")
	private String interest;
	// 收入 0: 其他 1: 0-2000
	@Column(name = "income")
	private Integer income;
	// 实名认证标志
	@Column(name = "identify_flag")
	private Integer identifyFlag;
	// 实名认证时间
	@JsonSerialize(using = JackJsonDateTimeFormat.class)
	@JsonDeserialize(using = JackJsonDateTimeParse.class)
	@Column(name = "identify_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date identifyTime;

	@KeyVersion
	@Column(name = "key_version")
	private Integer keyVersion;

	// 分页开始条数
	@Transient
	private Integer start;
	// 每页条数
	@Transient
	private Integer limit;
	// 注册查询起始日期
	@JsonSerialize(using = JackJsonDateFormat.class)
	@JsonDeserialize(using = JackJsonDateParse.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Transient
	private Date registerStart;
	// 注册查询截至日期
	@JsonSerialize(using = JackJsonDateFormat.class)
	@JsonDeserialize(using = JackJsonDateParse.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Transient
	private Date registerEnd;

	public Integer getKeyVersion() {
		return keyVersion;
	}

	public void setKeyVersion(Integer keyVersion) {
		this.keyVersion = keyVersion;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getFingerPrint() {
		return fingerPrint;
	}

	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getConstellatory() {
		return constellatory;
	}

	public void setConstellatory(Integer constellatory) {
		this.constellatory = constellatory;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Date getRegisterStart() {
		return registerStart;
	}

	public void setRegisterStart(Date registerStart) {
		this.registerStart = registerStart;
	}

	public Date getRegisterEnd() {
		return registerEnd;
	}

	public void setRegisterEnd(Date registerEnd) {
		this.registerEnd = registerEnd;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getIdentifyFlag() {
		return identifyFlag;
	}

	public void setIdentifyFlag(Integer identifyFlag) {
		this.identifyFlag = identifyFlag;
	}

	public Date getIdentifyTime() {
		return identifyTime;
	}

	public void setIdentifyTime(Date identifyTime) {
		this.identifyTime = identifyTime;
	}

	public MemberInfo(Long id, String userName, String nickName, String avatarUrl,Integer status, String gender, String fingerPrint,
			Date loginTime, Date addTime, Date updateTime, Integer delFlag, String mobileTel, String password,
			String email, Integer cardType, String idNumber, Integer integral, Integer level, String birthday,
			Integer constellatory, String occupation, String nation, String interest, Integer income,
			Integer identifyFlag, Date identifyTime,Integer keyVersion) {
		this.id = id;
		this.userName = userName;
		this.nickName = nickName;
		this.avatarUrl = avatarUrl;
		this.status = status;
		this.gender = gender;
		this.fingerPrint = fingerPrint;
		this.loginTime = loginTime;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.delFlag = delFlag;
		this.mobileTel = mobileTel;
		this.password = password;
		this.email = email;
		this.cardType = cardType;
		this.idNumber = idNumber;
		this.integral = integral;
		this.level = level;
		this.birthday = birthday;
		this.constellatory = constellatory;
		this.occupation = occupation;
		this.nation = nation;
		this.interest = interest;
		this.income = income;
		this.identifyFlag = identifyFlag;
		this.identifyTime = identifyTime;
		this.keyVersion = keyVersion;
	}

	public MemberInfo() {
		super();
	}

}
