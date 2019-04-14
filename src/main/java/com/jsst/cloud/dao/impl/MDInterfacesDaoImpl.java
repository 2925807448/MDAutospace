package com.jsst.cloud.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import com.jsst.cloud.base.Page;
import com.jsst.cloud.dao.IMDInterfacesDao;
import com.jsst.cloud.entity.MDInterfacesBodyParamData;
import com.jsst.cloud.entity.MDInterfacesBodyParamValue;
import com.jsst.cloud.entity.MDInterfacesInfo;
import com.jsst.cloud.entity.MDInterfacesParam;
import com.jsst.cloud.entity.MDInterfacesQueryParam;
import com.mongodb.DB;
import com.mongodb.MongoClient;

@Component
public class MDInterfacesDaoImpl extends MongodbDaoImpl<MDInterfacesInfo>
		implements IMDInterfacesDao {

	private static final Logger logger = LoggerFactory
			.getLogger(MDInterfacesDaoImpl.class);

	@Override
	protected Class<MDInterfacesInfo> getEntityClass() {
		return MDInterfacesInfo.class;
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoClient mongoClient;

	@Override
	public void save(String tableName, MDInterfacesInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchSave(String tableName, List<MDInterfacesInfo> lists) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MDInterfacesInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MDInterfacesInfo entity, String column, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MDInterfacesInfo entity, String[] wheresCols,
			String[] whereVals) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Serializable... ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public MDInterfacesInfo find(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MDInterfacesInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MDInterfacesInfo> findAll(String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MDInterfacesInfo> findByProp(String propName, Object propValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MDInterfacesInfo> findByProp(String propName, Object propValue,
			String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MDInterfacesInfo> findByProps(String[] propName,
			Object[] propValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MDInterfacesInfo> findByProps(String[] propName,
			Object[] propValue, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MDInterfacesInfo uniqueByProp(String propName, Object propValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MDInterfacesInfo uniqueByProps(String[] propName, Object[] propValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MDInterfacesInfo> pageAll(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MDInterfacesInfo> pageAll(int pageIndex, int pageSize,
			String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MDInterfacesInfo> pageByProp(int pageIndex, int pageSize,
			String param, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MDInterfacesInfo> pageByProp(int pageIndex, int pageSize,
			String param, Object value, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MDInterfacesInfo> pageByProps(int pageIndex, int pageSize,
			String[] params, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MDInterfacesInfo> pageByProps(int pageIndex, int pageSize,
			String[] params, Object[] values, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByCondition(String[] params, Object[] values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Map<String, Object>> queryPage(String tableName,
			Document condition, Document sort, Document queryFields,
			int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(String tableName, Document condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateData(String tableName, Map<String, Object> conditionMap,
			Map<String, Object> dataMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public MDInterfacesInfo queryInterfacesDetail(String id) {
		logger.info("请求类MDInterfacesInfo,ID:{}", id);
		MatchOperation match = Aggregation.match(new Criteria("id").is(id));
		Aggregation aggregation = Aggregation.newAggregation(match);

		AggregationResults<MDInterfacesInfo> results = mongoTemplate.aggregate(
				aggregation, "interfaces", MDInterfacesInfo.class);
		List<MDInterfacesInfo> list = results.getMappedResults();

		MDInterfacesInfo mdf = (list == null || list.isEmpty()) ? null : list
				.get(0);
		logger.info("MDInterfacesInfo:{}", mdf);
		return mdf;
	}
}
