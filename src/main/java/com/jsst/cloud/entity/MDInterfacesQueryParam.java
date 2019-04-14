package com.jsst.cloud.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import com.jsst.cloud.base.BaseEntity;



public class MDInterfacesQueryParam extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1466058653824818255L;
	@Field("name")
	private String name;
	@Field("must")
	private int must;
	@Field("remark")
	private String remark;
	private MDInterfacesBodyParamValue value;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setMust(int must) {
		this.must = must;
	}

	public int getMust() {
		return must;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setValue(MDInterfacesBodyParamValue value) {
		this.value = value;
	}

	public MDInterfacesBodyParamValue getValue() {
		return value;
	}

}
