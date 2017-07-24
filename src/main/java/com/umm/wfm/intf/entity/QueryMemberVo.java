package com.umm.wfm.intf.entity;

public class QueryMemberVo {

	String key;

	AccoutType type;

	public QueryMemberVo(String key, AccoutType type) {
		this.key = key;
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public AccoutType getType() {
		return type;
	}

	public void setType(AccoutType type) {
		this.type = type;
	}

}
