package com.jsst.cloud.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import com.alibaba.fastjson.annotation.JSONField;
import com.jsst.cloud.base.BaseEntity;


public class MDInterfacesParam extends BaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3366231340143185345L;
	private MDInterfacesBefore before;
	private MDInterfacesAfter after;
	@Field("name")
	private String name;
	@Field("id")
	private String id;
	@Field("remark")
	private String remark;
	
	@JSONField(serialize = false)
	private List<MDInterfacesHeader> header;
	
	@JSONField(serialize = false)
	private List<MDInterfacesQueryParam> queryParam;
	
	@JSONField(serialize = false)
	private List<MDInterfacesBodyParam> bodyParam;
	
	private MDInterfacesBodyInfo bodyInfo;
	
	@JSONField(serialize = false)
	private List<MDInterfacesOutParam> outParam;
	
	private MDInterfacesOutInfo outInfo;
	
	@JSONField(serialize = false)
	private List<MDInterfacesRestParam> restParam;

	public MDInterfacesBefore getBefore() {
		return before;
	}

	public void setBefore(MDInterfacesBefore before) {
		this.before = before;
	}

	public MDInterfacesAfter getAfter() {
		return after;
	}

	public void setAfter(MDInterfacesAfter after) {
		this.after = after;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<MDInterfacesHeader> getHeader() {
		return header;
	}

	public void setHeader(List<MDInterfacesHeader> header) {
		this.header = header;
	}

	public List<MDInterfacesQueryParam> getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(List<MDInterfacesQueryParam> queryParam) {
		this.queryParam = queryParam;
	}

	public List<MDInterfacesBodyParam> getBodyParam() {
		return bodyParam;
	}

	public void setBodyParam(List<MDInterfacesBodyParam> bodyParam) {
		this.bodyParam = bodyParam;
	}

	public MDInterfacesBodyInfo getBodyInfo() {
		return bodyInfo;
	}

	public void setBodyInfo(MDInterfacesBodyInfo bodyInfo) {
		this.bodyInfo = bodyInfo;
	}

	public List<MDInterfacesOutParam> getOutParam() {
		return outParam;
	}

	public void setOutParam(List<MDInterfacesOutParam> outParam) {
		this.outParam = outParam;
	}

	public MDInterfacesOutInfo getOutInfo() {
		return outInfo;
	}

	public void setOutInfo(MDInterfacesOutInfo outInfo) {
		this.outInfo = outInfo;
	}

	public List<MDInterfacesRestParam> getRestParam() {
		return restParam;
	}

	public void setRestParam(List<MDInterfacesRestParam> restParam) {
		this.restParam = restParam;
	}

}
