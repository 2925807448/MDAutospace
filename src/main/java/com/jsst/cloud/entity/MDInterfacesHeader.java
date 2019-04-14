package com.jsst.cloud.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import com.jsst.cloud.base.BaseEntity;

public class MDInterfacesHeader extends BaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5861260880655154901L;
	@Field("remark")
	private String remark;
	@Field("value")
	private String value;
	@Field("name")
	private String name;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
