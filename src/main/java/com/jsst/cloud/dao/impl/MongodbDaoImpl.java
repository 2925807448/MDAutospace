package com.jsst.cloud.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.jsst.cloud.base.Page;
import com.jsst.cloud.dao.IMongodbDao;
import com.jsst.cloud.utils.ClassUtils;
import com.jsst.cloud.utils.EmptyUtil;
import com.jsst.cloud.utils.JsonUtil;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

@Configuration
@Component
public abstract class MongodbDaoImpl<T> implements IMongodbDao<T> {

	private static final Logger logger = LoggerFactory.getLogger(MongodbDaoImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private MongoDatabase mongoDatabase;

	protected abstract Class<T> getEntityClass();

	@Override
	public void save(String tableName, T entity) {
		// 获取对象中属性对应的字段及其值
		Map<String, Object> map = ClassUtils.getColumnValue(entity);

		logger.info("新增数据：{}", JSON.toJSONString(map));

		// 数据
		Document datas = new Document();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			datas.append(entry.getKey(), entry.getValue());
		}

		MongoCollection<Document> collection = mongoDatabase.getCollection(tableName);

		collection.insertOne(datas);
	}

	public void batchSave(String tableName, List<T> lists) {

		if (lists == null || lists.isEmpty()) {
			logger.info("批量保存数据，数据为空");
			return;
		}

		List<Document> datas = new ArrayList<Document>();

		for (T t : lists) {
			// 获取对象中属性对应的字段及其值
			Map<String, Object> map = ClassUtils.getColumnValue(t);

			Document data = new Document();

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				data.append(entry.getKey(), entry.getValue());
			}
			datas.add(data);
		}

		if (!datas.isEmpty()) {
			MongoCollection<Document> collection = mongoDatabase.getCollection(tableName);

			collection.insertMany(datas);
		}
	}

	@Override
	public void update(T entity) {
		// 反向解析对象
		Map<String, Object> map = null;
		try {
			map = parseEntity(entity);
		} catch (Exception e) {
			logger.error("反向解析对象失败:" + e.getMessage(), e);
		}

		// ID字段
		String idName = null;
		Object idValue = null;

		// 生成参数
		Update update = new Update();
		if (EmptyUtil.isNotEmpty(map)) {
			for (String key : map.keySet()) {
				if (key.indexOf("{") != -1) {
					// 设置ID
					idName = key.substring(key.indexOf("{") + 1, key.indexOf("}"));
					idValue = map.get(key);
				} else {
					if (!isNull(map.get(key))) {

						Object obj = map.get(key);

						if (obj instanceof String) {

							String value = (String) obj;

							if ("UNKOWN".equalsIgnoreCase(value)) {
								value = "";
							}

							update.set(key, value);
						} else {
							update.set(key, map.get(key));
						}
					}
				}
			}
		}
		mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where(idName).is(idValue)), update,
				getEntityClass());
	}

	/**
	 * 根据指定的字段更新数据
	 * 
	 * @param entity
	 * @param column
	 * @param value
	 * @see com.jieshun.jht.module.mongodb.dao.IMongodbDao#update(java.lang.Object,
	 *      java.lang.String, java.lang.String)
	 * @author
	 * @date 2019年1月5日
	 * @since JDK 1.8
	 */
	public void update(T entity, String column, String value) {
		// 反向解析对象
		Map<String, Object> map = null;
		try {
			map = parseEntity(entity);
		} catch (Exception e) {
			logger.error("反向解析对象失败:" + e.getMessage(), e);
		}

		// 生成参数
		Update update = new Update();
		if (EmptyUtil.isNotEmpty(map)) {
			for (String key : map.keySet()) {
				if (!isNull(map.get(key))) {
					if (!column.equalsIgnoreCase(key)) {
						/* 不更新指定的字段 */
						// 值
						Object obj = map.get(key);

						if (obj instanceof String) {

							String str = (String) obj;

							if ("UNKOWN".equalsIgnoreCase(str)) {
								str = "";
							}

							update.set(key, str);
						} else {
							update.set(key, map.get(key));
						}
					}
				}
			}
		}
		mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where(column).is(value)), update, getEntityClass());
	}

	/**
	 * 根据多个where条件修改
	 * 
	 * @param entity
	 *            数据
	 * @param wheresCols
	 *            where条件的字段
	 * @param whereVals
	 *            where条件的字段值
	 * @see com.jieshun.jht.module.mongodb.dao.IMongodbDao#update(java.lang.Object,
	 *      java.lang.String[], java.lang.String[])
	 * @author
	 * @date 2019年1月8日
	 * @since JDK 1.8
	 */
	public void update(T entity, String[] wheresCols, String[] whereVals) {

		// 反向解析对象
		Map<String, Object> map = null;
		try {
			map = parseEntity(entity);
		} catch (Exception e) {
			logger.error("反向解析对象失败:" + e.getMessage(), e);
		}

		Query query = this.createQuery(wheresCols, whereVals, null);

		// 生成参数
		Update update = new Update();

		if (EmptyUtil.isNotEmpty(map)) {
			for (String key : map.keySet()) {
				if (!isNull(map.get(key))) {
					for (String column : wheresCols) {
						if (!column.equalsIgnoreCase(key)) {
							/* 不更新指定的字段 */
							// 值
							Object obj = map.get(key);

							if (obj instanceof String) {

								String value = (String) obj;

								if ("UNKOWN".equalsIgnoreCase(value)) {
									value = "";
								}

								update.set(key, value);
							} else {
								update.set(key, map.get(key));
							}
						}
					}
				}
			}
		}
		mongoTemplate.updateFirst(query, update, getEntityClass());
	}

	@Override
	public void delete(Serializable... ids) {
		if (EmptyUtil.isNotEmpty(ids)) {
			for (Serializable id : ids) {
				mongoTemplate.remove(mongoTemplate.findById(id, getEntityClass()));
			}
		}
	}

	@Override
	public T find(Serializable id) {
		return mongoTemplate.findById(id, getEntityClass());
	}

	@Override
	public List<T> findAll() {
		return mongoTemplate.findAll(getEntityClass());
	}

	@Override
	public List<T> findAll(String order) {
		List<Order> orderList = parseOrder(order);
		if (EmptyUtil.isEmpty(orderList)) {
			return findAll();
		}
		// return mongoTemplate.find(new Query().with(new Sort(orderList)),
		// getEntityClass());
		return mongoTemplate.find(new Query().with(Sort.by(orderList)), getEntityClass());
	}

	@Override
	public List<T> findByProp(String propName, Object propValue) {
		return findByProp(propName, propValue, null);
	}

	@Override
	public List<T> findByProp(String propName, Object propValue, String order) {
		Query query = new Query();
		// 参数
		query.addCriteria(Criteria.where(propName).is(propValue));
		// 排序
		List<Order> orderList = parseOrder(order);

		if (EmptyUtil.isNotEmpty(orderList)) {
			// query.with(new Sort(orderList));
			query.with(Sort.by(orderList));
		}
		return mongoTemplate.find(query, getEntityClass());
	}

	@Override
	public List<T> findByProps(String[] propName, Object[] propValue) {
		return findByProps(propName, propValue, null);
	}

	@Override
	public List<T> findByProps(String[] propName, Object[] propValue, String order) {
		Query query = createQuery(propName, propValue, order);
		return mongoTemplate.find(query, getEntityClass());
	}

	@Override
	public T uniqueByProp(String propName, Object propValue) {
		return mongoTemplate.findOne(new Query(Criteria.where(propName).is(propValue)), getEntityClass());
	}

	@Override
	public T uniqueByProps(String[] propName, Object[] propValue) {
		Query query = createQuery(propName, propValue, null);
		return mongoTemplate.findOne(query, getEntityClass());
	}

	@Override
	public Page<T> pageAll(int pageIndex, int pageSize) {
		return pageAll(pageIndex, pageSize, null);
	}

	@Override
	public Page<T> pageAll(int pageIndex, int pageSize, String order) {
		return pageByProp(pageIndex, pageSize, null, null, order);
	}

	@Override
	public Page<T> pageByProp(int pageIndex, int pageSize, String param, Object value) {
		return pageByProp(pageIndex, pageSize, param, value, null);
	}

	@Override
	public Page<T> pageByProp(int pageIndex, int pageSize, String param, Object value, String order) {
		String[] params = null;
		Object[] values = null;
		if (EmptyUtil.isNotEmpty(param)) {
			params = new String[] { param };
			values = new Object[] { value };
		}
		return pageByProps(pageIndex, pageSize, params, values, order);
	}

	@Override
	public Page<T> pageByProps(int pageIndex, int pageSize, String[] params, Object[] values) {
		return pageByProps(pageIndex, pageSize, params, values, null);
	}

	@Override
	public Page<T> pageByProps(int pageIndex, int pageSize, String[] params, Object[] values, String order) {
		// 创建分页模型对象
		/* Page<T> page = new Page<>(pageIndex, pageSize); */
		Page<T> page = new Page<T>(new ArrayList<T>(), 0L, 1, 10);

		// 查询总记录数
		int count = countByCondition(params, values);
		// page.setTotalCount(count);

		// 查询数据列表
		Query query = createQuery(params, values, order);

		// 设置分页信息
		query.skip((pageIndex - 1) * pageSize);
		query.limit(pageSize);

		// 封装结果数据
		if (count > 0) {
			page = new Page<T>(mongoTemplate.find(query, getEntityClass()), count, pageIndex, pageSize);
		}

		return page;
	}

	@Override
	public int countByCondition(String[] params, Object[] values) {
		Query query = createQuery(params, values, null);
		Long count = mongoTemplate.count(query, getEntityClass());
		return count.intValue();
	}

	/**
	 * 分页查询
	 * 
	 * @param tableName
	 *            表名
	 * @param condition
	 *            查询条件
	 * @param sort
	 *            排序. 1：升序排序，-1：降序排序
	 * @param queryFields
	 *            查询字段
	 * @param pageIndex
	 *            当前页数
	 * @param pageSize
	 *            每页数据量
	 * @return List
	 * @author
	 * @date 2019年3月1日
	 * @since JDK 1.8
	 */
	@Override
	public List<Map<String, Object>> queryPage(final String tableName, final Document condition, final Document sort,
			final Document queryFields, int pageIndex, int pageSize) {

		// MongoDatabase mongoDatabase = getMongoClient().getDatabase(database);

		logger.info("分页查询，mongoDatabase：{}", mongoDatabase);
		logger.info("分页查询，tableName：{}，condition：{}，sort：{}，queryFields：{}，pageIndex：{}，pageSize：{}", tableName,
				JsonUtil.toJSON(condition), JsonUtil.toJSON(sort), JsonUtil.toJSON(queryFields), pageIndex, pageSize);

		pageIndex = (pageIndex <= 0) ? 1 : pageIndex;
		pageSize = (pageSize <= 0) ? 10 : pageSize;

		MongoCollection<Document> collection = mongoDatabase.getCollection(tableName);

		// if (secondRead) {
		// logger.info("优先从节点读取数据");
		// // 优先从节点读取数据
		// collection = collection
		// .withReadPreference(ReadPreference.secondaryPreferred(maxStaleness,
		// TimeUnit.SECONDS));
		// }

		FindIterable<Document> iterable = null;

		if (pageIndex > 0) {
			if (queryFields != null && queryFields.size() > 0) {
				iterable = collection.find(condition).sort(sort).skip((pageIndex - 1) * pageSize).limit(pageSize)
						.projection(queryFields);
			} else {
				iterable = collection.find(condition).sort(sort).skip((pageIndex - 1) * pageSize).limit(pageSize);
			}
		} else {
			if (queryFields != null && queryFields.size() > 0) {
				iterable = collection.find(condition).sort(sort).limit(pageSize).projection(queryFields);
			} else {
				iterable = collection.find(condition).sort(sort).limit(pageSize);
			}
		}
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		iterable.forEach(new Block<Document>() {
			public void apply(final Document document) {

				Iterator<String> iterator = document.keySet().iterator();

				Map<String, Object> map = new HashMap<String, Object>();

				while (iterator.hasNext()) {
					String key = iterator.next();
					map.put(key, document.get(key));
				}
				if (map.size() > 0) {
					returnList.add(map);
				}
			}
		});
		return returnList;
	}

	/**
	 * 查询满足条件的数据量
	 * 
	 * @param tableName
	 *            表名
	 * @param condition
	 *            查询条件
	 * @return long
	 * @author
	 * @date 2019年3月1日
	 * @since JDK 1.8
	 */
	@Override
	public long count(final String tableName, final Document condition) {

		// MongoDatabase mongoDatabase = getMongoClient().getDatabase(database);

		logger.info("查询数据量，mongoDatabase：{}", mongoDatabase);
		logger.info("查询数据量，tableName：{}，condition：{}", tableName, JsonUtil.toJSON(condition));

		MongoCollection<Document> collection = mongoDatabase.getCollection(tableName);

		// if (secondRead) {
		// logger.info("优先从节点读取数据");
		// // 优先从节点读取数据
		// collection = collection
		// .withReadPreference(ReadPreference.secondaryPreferred(maxStaleness,
		// TimeUnit.SECONDS));
		// }

		return collection.countDocuments(condition);
	}

	/**
	 * 创建带有where条件（只支持等值）和排序的Query对象
	 * 
	 * @param params
	 *            参数数组
	 * @param values
	 *            参数值数组
	 * @param order
	 *            排序
	 * @return Query对象
	 */
	protected Query createQuery(String[] params, Object[] values, String order) {
		Query query = new Query();

		// where 条件
		if (EmptyUtil.isNotEmpty(params) && EmptyUtil.isNotEmpty(values)) {
			for (int i = 0; i < params.length; i++) {
				if (EmptyUtil.isNotEmpty(params[i]) && !isNull(values[i])) {
					query.addCriteria(Criteria.where(params[i]).is(values[i]));
				}

			}
		}

		// 排序
		List<Order> orderList = parseOrder(order);

		if (EmptyUtil.isNotEmpty(orderList)) {
			// query.with(new Sort(orderList));
			query.with(Sort.by(orderList));
		}

		return query;
	}

	/**
	 * 解析Order字符串为所需参数
	 * 
	 * @param order
	 *            排序参数，如[id]、[id asc]、[id asc,name desc]
	 * @return Order对象集合
	 */
	protected List<Order> parseOrder(String order) {
		List<Order> list = null;
		if (EmptyUtil.isNotEmpty(order)) {
			list = new ArrayList<Order>();
			// 共有几组排序字段
			String[] fields = order.split(",");
			Order o = null;
			String[] item = null;
			for (int i = 0; i < fields.length; i++) {
				if (EmptyUtil.isEmpty(fields[i])) {
					continue;
				}
				item = fields[i].split(" ");
				if (item.length == 1) {
					o = new Order(Direction.ASC, item[0]);
				} else if (item.length == 2) {
					o = new Order("desc".equalsIgnoreCase(item[1]) ? Direction.DESC : Direction.ASC, item[0]);
				} else {
					throw new RuntimeException("排序字段参数解析出错");
				}
				list.add(o);
			}
		}
		return list;
	}

	/**
	 * 将对象的字段及值反射解析为Map对象<br>
	 * 这里使用Java反射机制手动解析，并且可以识别注解为主键的字段，以达到根据id进行更新实体的目的<br>
	 * key：字段名称，value：字段对应的值
	 * 
	 * @param t
	 *            要修改的对象
	 * @return Map对象，注意：id字段的key封装为“{id字段名称}”，以供后续识别
	 * @throws Exception
	 */
	protected Map<String, Object> parseEntity(T t) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * 解析ID
		 */
		String idName = "";
		Field[] declaredFields = getEntityClass().getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(Id.class)) {
				field.setAccessible(true);
				map.put("{" + field.getName() + "}", field.get(t));
				idName = field.getName();
				break;
			}
		}
		// 获取所有方法,包括父类方法
		List<Method> methodList = new ArrayList<>();

		Class<?> clazz = t.getClass();

		while (clazz != null) {
			// 当父类为null的时候说明到达了最上层的父类(Object类).
			methodList.addAll(Arrays.asList(clazz.getDeclaredMethods()));

			clazz = clazz.getSuperclass(); // 得到父类,然后赋给自己
		}

		/*
		 * 解析其他属性
		 */
		// Method[] methods = getEntityClass().getDeclaredMethods();

		if (EmptyUtil.isNotEmpty(methodList)) {
			for (Method method : methodList) {
				if (method.getName().startsWith("get") && method.getModifiers() == Modifier.PUBLIC) {
					String fieldName = parse2FieldName(method.getName());
					if (!fieldName.equals(idName)) {
						map.put(fieldName, method.invoke(t));
					}
				}
			}
		}

		logger.info(t.getClass() + " methods:{}", JsonUtil.toJSON(map));

		return map;
	}

	/**
	 * 将get方法名转换为对应的字段名称
	 * 
	 * @param methodName
	 *            如：getName
	 * @return 如：name
	 */
	private String parse2FieldName(String methodName) {
		String name = methodName.replace("get", "");
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		return name;
	}

	protected boolean isNull(Object obj) {

		if (obj == null) {
			return true;
		}

		if (obj instanceof String) {
			String s = (String) obj;
			return s == null || "".equals(s.trim()) || "null".equalsIgnoreCase(s.trim());
		}

		if (obj instanceof Integer) {
			Integer i = ((Integer) obj).intValue();
			return i == null;
		}

		if (obj instanceof Long) {
			Long i = ((Long) obj).longValue();
			return i == null;
		}

		if (obj instanceof BigDecimal) {
			BigDecimal bd = (BigDecimal) obj;
			return bd == null || bd.compareTo(BigDecimal.ZERO) == 0;
		}

		return false;
	}

	/**
	 * 更新,不存在则插入
	 * 
	 * @param tableName
	 *            表名
	 * @param conditionMap
	 *            更新条件
	 * @param dataMap
	 *            数据
	 * @author
	 * @date 2019年3月4日
	 * @since JDK 1.8
	 */
	@Override
	public void updateData(String tableName, Map<String, Object> conditionMap, Map<String, Object> dataMap) {

		// MongoDatabase mongoDatabase = getMongoClient().getDatabase(database);

		logger.info("修改数据，入参为：tableName：{}，conditionMap：{}，dataMap:{}", tableName, conditionMap, dataMap);

		if (conditionMap == null || conditionMap.isEmpty() || dataMap == null || dataMap.isEmpty()) {
			logger.info("修改数据，条件或数据为空");
			return;
		}

		MongoCollection<Document> collection = mongoDatabase.getCollection(tableName);

		// 更新条件
		Document condition = new Document();

		for (Map.Entry<String, Object> entry : conditionMap.entrySet()) {
			condition.append(entry.getKey(), entry.getValue());
		}

		// 数据
		Document updateData = new Document();

		for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
			updateData.append(entry.getKey(), entry.getValue());
		}
		Document modifiers = new Document();
		modifiers.append("$set", updateData);

		collection.updateMany(condition, modifiers, new UpdateOptions().upsert(true));
	}

}
