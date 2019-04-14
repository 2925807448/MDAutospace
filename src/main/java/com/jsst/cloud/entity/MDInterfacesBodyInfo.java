package com.jsst.cloud.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import com.jsst.cloud.base.BaseEntity;

public class MDInterfacesBodyInfo extends BaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4000462097455431004L;
	@Field("rawText")
	private String rawText;
	@Field("rawFileRemark")
	private String rawFileRemark;
	@Field("rawTextRemark")
	private String rawTextRemark;
	@Field("rawType")
	private String rawType;
	@Field("type")
	private String type;

	public String getRawText() {
		return rawText;
	}

	public void setRawText(String rawText) {
		this.rawText = rawText;
	}

	public String getRawFileRemark() {
		return rawFileRemark;
	}

	public void setRawFileRemark(String rawFileRemark) {
		this.rawFileRemark = rawFileRemark;
	}

	public String getRawTextRemark() {
		return rawTextRemark;
	}

	public void setRawTextRemark(String rawTextRemark) {
		this.rawTextRemark = rawTextRemark;
	}

	public String getRawType() {
		return rawType;
	}

	public void setRawType(String rawType) {
		this.rawType = rawType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
