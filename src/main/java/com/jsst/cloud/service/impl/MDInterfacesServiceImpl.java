package com.jsst.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsst.cloud.dao.IMDInterfacesDao;
import com.jsst.cloud.entity.MDInterfacesInfo;
import com.jsst.cloud.service.IMDInterfacesService;

@Service("interfacesService")
public class MDInterfacesServiceImpl implements IMDInterfacesService {

	@Autowired
	IMDInterfacesDao interfacesDao;

	@Override
	public MDInterfacesInfo queryInterfacesDetail(String id) {
		// TODO Auto-generated method stub
		return interfacesDao.queryInterfacesDetail(id);
	}

}
