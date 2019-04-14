package com.jsst.cloud.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.jsst.cloud.base.Page;
import com.jsst.cloud.dao.IMDInterfacesCaseDao;
import com.jsst.cloud.entity.MDInterfacesCase;
import com.jsst.cloud.entity.MDInterfacesInfo;
import com.mongodb.BasicDBObject;

@Component
public class MDInterfacesCaseDaoImpl extends MongodbDaoImpl<MDInterfacesCase>
		implements IMDInterfacesCaseDao {

	private static final Logger logger = LoggerFactory
			.getLogger(MDInterfacesCaseDaoImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public MDInterfacesCase queryInterfaceCaseByNo(String caseNo) {
		// TODO Auto-generated method stub
		logger.info("请求类MDInterfacesCase-开始,caseNo:{}", caseNo);
		MatchOperation match = Aggregation.match(new Criteria("case_no")
				.is(caseNo));
		Aggregation aggregation = Aggregation.newAggregation(match);

		AggregationResults<MDInterfacesCase> results = mongoTemplate.aggregate(
				aggregation, "cases", MDInterfacesCase.class);
		List<MDInterfacesCase> list = results.getMappedResults();
		MDInterfacesCase mdf = (list == null || list.isEmpty()) ? null : list
				.get(0);
		logger.info("请求类MDInterfacesCase-结束:{}", mdf);
		return mdf;
	}

	@Override
	public List<String> queryInterfaceCaseByNos() {
		// TODO Auto-generated method stub
		logger.info("请求类MDInterfacesCase-开始");
		// 查询条件
		BasicDBObject queryObject = new BasicDBObject();

		// 指定返回的字段
		BasicDBObject fieldsObject = new BasicDBObject();
		fieldsObject.put("_id", false);
		fieldsObject.put("case_no", true);

		Query query = new BasicQuery(queryObject.toJson(),
				fieldsObject.toJson());

		List<String> results = mongoTemplate.find(query, String.class, "cases");

		if (!results.isEmpty() && results.size() > 0) {
			return results;
		}
		logger.info("请求类MDInterfacesCase-结束");
		return null;
	}

	@Override
	public List<MDInterfacesCase> queryInterfaceCaseList(List<String> list) {
		// TODO Auto-generated method stub
		logger.info("请求类MDInterfacesCase-开始,请求参数：{}", list.toString());
		LookupOperation interfacesLookup = LookupOperation.newLookup()
				.from("interfaces").localField("id").foreignField("id")
				.as("interfacesinfo");
		MatchOperation match = Aggregation.match(new Criteria("interface_id")
				.in(list));
		Aggregation aggregation = Aggregation.newAggregation(interfacesLookup,
				match);
		AggregationResults<MDInterfacesCase> results = mongoTemplate.aggregate(
				aggregation, "cases", MDInterfacesCase.class);
		return results.getMappedResults();
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
	protected Class<MDInterfacesCase> getEntityClass() {
		// TODO Auto-generated method stub
		return MDInterfacesCase.class;
	}

	@Override
	public void saveInterfacesCase(MDInterfacesCase interfacesCase) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(interfacesCase);
	}

}
