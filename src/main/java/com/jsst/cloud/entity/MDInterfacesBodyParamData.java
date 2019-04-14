package com.jsst.cloud.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import com.jsst.cloud.base.BaseEntity;

public class MDInterfacesBodyParamData extends BaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1591351087279424438L;
	@Field("value")
	private String value;
	@Field("remark")
	private String remark;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
