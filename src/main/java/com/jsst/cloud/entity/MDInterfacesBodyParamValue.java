package com.jsst.cloud.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.alibaba.fastjson.annotation.JSONField;
import com.jsst.cloud.base.BaseEntity;

public class MDInterfacesBodyParamValue extends BaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1734203604667013636L;
	@Field("type")
	private int type;
	@JSONField(serialize=false)
	private List<MDInterfacesBodyParamData> data;
	@Field("status")
	private String status;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<MDInterfacesBodyParamData> getData() {
		return data;
	}

	public void setData(List<MDInterfacesBodyParamData> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
