package com.jsst.cloud.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import com.jsst.cloud.base.BaseEntity;

public class MDInterfacesBefore extends BaseEntity  {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5557103509685664519L;
	@Field("mode")
	private int mode;
	@Field("code")
	private String code;

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
