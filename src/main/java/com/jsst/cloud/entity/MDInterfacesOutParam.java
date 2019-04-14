package com.jsst.cloud.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import com.jsst.cloud.base.BaseEntity;

public class MDInterfacesOutParam extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7960533963420486209L;
	@Field("name")
	private String name;
	@Field("type")
	private String type;
	@Field("remark")
	private String remark;
	@Field("must")
	private String must;
	@Field("mock")
	private String mock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMust() {
		return must;
	}

	public void setMust(String must) {
		this.must = must;
	}

	public String getMock() {
		return mock;
	}

	public void setMock(String mock) {
		this.mock = mock;
	}

}
