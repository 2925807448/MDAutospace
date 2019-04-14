package com.jsst.cloud.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import com.jsst.cloud.base.BaseEntity;

public class MDInterfacesOutInfo extends BaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4233171957505833726L;
	@Field("jsonType")
	private String jsonType;
	@Field("rawMock")
	private String rawMock;
	@Field("rawRemark")
	private String rawRemark;
	@Field("type")
	private String type;
	public String getJsonType() {
		return jsonType;
	}
	public void setJsonType(String jsonType) {
		this.jsonType = jsonType;
	}
	public String getRawMock() {
		return rawMock;
	}
	public void setRawMock(String rawMock) {
		this.rawMock = rawMock;
	}
	public String getRawRemark() {
		return rawRemark;
	}
	public void setRawRemark(String rawRemark) {
		this.rawRemark = rawRemark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
