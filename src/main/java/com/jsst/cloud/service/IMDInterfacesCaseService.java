package com.jsst.cloud.service;

import java.util.List;
import java.util.Map;

import com.jsst.cloud.base.Page;
import com.jsst.cloud.entity.MDInterfacesCase;

public interface IMDInterfacesCaseService {
	public MDInterfacesCase queryInterfaceCaseByNo(String caseNo);

	public List<String> queryInterfaceCaseByNos();

	public List<MDInterfacesCase> queryInterfaceCaseList(List<String> list);

	public List<MDInterfacesCase> queryInterfaceCaseListByPage(int startIndex,
			int pageSize);

	public Page<MDInterfacesCase> queryInterfaceCaseListByPage(int pageIndex,
			int pageSize, String[] params, Object[] values, String order);

	public Page<MDInterfacesCase> queryInterfaceCaseListByPage(int pageIndex,
			int pageSize, Map<String, Object> params);

	public void saveInterfacesCase(MDInterfacesCase interfacesCase);
}
