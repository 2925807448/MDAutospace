package com.jsst.cloud.entity;

import com.jsst.cloud.base.BaseEntity;

public class MDInterfacesRestParam extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8068498627670168854L;
	
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
