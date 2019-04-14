package com.jsst.cloud.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsst.cloud.base.BaseEntity;

@Document(collection = "cases")
public class MDInterfacesCase extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3106579210784068474L;

	@Field("id")
	private String id;

	@Field("case_no")
	private String caseNo;

	@Field("interface_id")
	private String interfaceId;

	@Field("case_name")
	private String caseName;

	@Field("case_desc")
	private String caseDesc;

	@Field("expect_ouput")
	private String expectOutput;

	@Field("real_ouput")
	private String realOutput;

	@Field("table_check")
	private boolean isTableCheck;

	@Field("redis_check")
	private boolean isRedisCheck;

	@Field("project_id")
	private String projectId;

	@Field("module_id")
	private String moduleId;

	@Field("group_id")
	private String groupId;

	@Field("req_url")
	private String reqUrl;

	@Field("remark")
	private String remark;

	@Field("method_name")
	private String methodName;

	@Field("creator")
	private String createId;

	@Field("editor")
	private String editorId;
	
	@Field("is_run")
	private String isRun;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	@Field("createdAt")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	@Field("updatedAt")
	private Date updateTime;

	@Field("version")
	private String version;
	
	public String getIsRun() {
		return isRun;
	}

	public void setIsRun(String isRun) {
		this.isRun = isRun;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseDesc() {
		return caseDesc;
	}

	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}

	public String getExpectOutput() {
		return expectOutput;
	}

	public void setExpectOutput(String expectOutput) {
		this.expectOutput = expectOutput;
	}

	public String getRealOutput() {
		return realOutput;
	}

	public void setRealOutput(String realOutput) {
		this.realOutput = realOutput;
	}

	public boolean isTableCheck() {
		return isTableCheck;
	}

	public void setTableCheck(boolean isTableCheck) {
		this.isTableCheck = isTableCheck;
	}

	public boolean isRedisCheck() {
		return isRedisCheck;
	}

	public void setRedisCheck(boolean isRedisCheck) {
		this.isRedisCheck = isRedisCheck;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
