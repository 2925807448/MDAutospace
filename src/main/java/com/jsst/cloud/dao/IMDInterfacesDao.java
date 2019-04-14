package com.jsst.cloud.dao;

import com.jsst.cloud.entity.MDInterfacesInfo;

public interface IMDInterfacesDao extends IMongodbDao<MDInterfacesInfo> {
	
	public MDInterfacesInfo queryInterfacesDetail(String id);
}
