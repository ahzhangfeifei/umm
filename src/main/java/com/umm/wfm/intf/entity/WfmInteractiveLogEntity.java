package com.umm.wfm.intf.entity;

import lombok.Data;

import java.util.Date;

/**
 * fmc_interactive_log表的 实体类
 * @author qifei
 *
 */
@Data
public class WfmInteractiveLogEntity {
	
	private Integer id;
	
	private Integer seqId;
	
	private String action;
	
	private String tspName;
	
	private String reqJson;
	
	private String respJson;
	
	private Date addTime;
	
	private Date updateTime;
	
	private Integer delFlag;

}
