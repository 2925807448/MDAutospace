package com.jsst.cloud.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsst.cloud.base.Page;
import com.jsst.cloud.dao.IMDInterfacesCaseDao;
import com.jsst.cloud.dao.impl.MDInterfacesDaoImpl;
import com.jsst.cloud.entity.MDInterfacesCase;
import com.jsst.cloud.service.IMDInterfacesCaseService;

@Service("interfacesCaseService")
public class MDInterfacesCaseServiceCaseImpl implements
		IMDInterfacesCaseService {
	private static final Logger logger = LoggerFactory
			.getLogger(MDInterfacesCaseServiceCaseImpl.class);

	@Autowired
	IMDInterfacesCaseDao InterfacesCaseDao;

	@Override
	public MDInterfacesCase queryInterfaceCaseByNo(String caseNo) {
		// TODO Auto-generated method stub
		logger.info("请求类MDInterfacesCaseServiceCaseImpl-开始,case_no:{}", caseNo);
		return InterfacesCaseDao.queryInterfaceCaseByNo(caseNo);
	}

	@Override
	public List<String> queryInterfaceCaseByNos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MDInterfacesCase> queryInterfaceCaseList(List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MDInterfacesCase> queryInterfaceCaseListByPage(int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MDInterfacesCase> queryInterfaceCaseListByPage(int pageIndex,
			int pageSize, String[] params, Object[] values, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MDInterfacesCase> queryInterfaceCaseListByPage(int pageIndex,
			int pageSize, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInterfacesCase(MDInterfacesCase interfacesCase) {
		// TODO Auto-generated method stub
		InterfacesCaseDao.saveInterfacesCase(interfacesCase);
	}

}
