package com.jsst.cloud.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsst.cloud.base.BaseEntity;

@Document(collection = "interfaces")
public class MDInterfacesInfo extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5120309978274892525L;
	@Field("id")
	private String id;
	
	@JSONField(serialize=false)
	private List<MDInterfacesParam> param;
	
	@Field("finish")
	private int finish;
	@Field("sort")
	private int sort;
	@Field("name")
	private String name;
	@Field("project")
	private String projectId;
	@Field("group")
	private String groupId;
	@Field("url")
	private String url;
	@Field("remark")
	private String remark;
	@Field("method")
	private String method;
	@Field("owner")
	private String ownerId;
	@Field("editor")
	private String editorId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	@Field("createdAt")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	@Field("updatedAt")
	private Date updateTime;
	@Field("__v")
	private int version;
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getEditorId() {
		return editorId;
	}

	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<MDInterfacesParam> getParam() {
		return param;
	}

	public void setParam(List<MDInterfacesParam> param) {
		this.param = param;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
