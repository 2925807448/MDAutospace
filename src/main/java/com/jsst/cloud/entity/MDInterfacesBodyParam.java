package com.jsst.cloud.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import com.jsst.cloud.base.BaseEntity;

public class MDInterfacesBodyParam extends BaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6171042173870400188L;
	@Field("remark")
	private String remark;
	@Field("must")
	private int must;
	@Field("type")
	private int type;
	@Field("name")
	private String name;
	private MDInterfacesBodyParamValue value;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getMust() {
		return must;
	}
	public void setMust(int must) {
		this.must = must;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MDInterfacesBodyParamValue getValue() {
		return value;
	}
	public void setValue(MDInterfacesBodyParamValue value) {
		this.value = value;
	}
	
}
