package com.jsst.cloud.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.bson.Document;

import com.jsst.cloud.base.Page;

public interface IMongodbDao<T> {

	public void save(String tableName, T entity);

	public void batchSave(String tableName, List<T> lists);

	public void update(T entity);

	/**
	 * 根据指定的字段更新数据
	 * 
	 * @param entity
	 * @param column
	 *            字段
	 * @param value
	 *            字段值
	 * @author 
	 * @date 2019年1月5日
	 * @since JDK 1.8
	 */
	public void update(T entity, String column, String value);

	/**
	 * 根据多个where条件修改
	 * 
	 * @param entity
	 *            数据
	 * @param wheresCols
	 *            where条件的字段
	 * @param whereVals
	 *            where条件的字段值
	 * @author 
	 * @date 2019年1月8日
	 * @since JDK 1.8
	 */
	public void update(T entity, String[] wheresCols, String[] whereVals);

	public void delete(Serializable... ids);

	public T find(Serializable id);

	public List<T> findAll();

	public List<T> findAll(String order);

	public List<T> findByProp(String propName, Object propValue);

	public List<T> findByProp(String propName, Object propValue, String order);

	public List<T> findByProps(String[] propName, Object[] propValue);

	public List<T> findByProps(String[] propName, Object[] propValue,
			String order);

	public T uniqueByProp(String propName, Object propValue);

	public T uniqueByProps(String[] propName, Object[] propValue);

	public Page<T> pageAll(int pageIndex, int pageSize);

	public Page<T> pageAll(int pageIndex, int pageSize, String order);

	public Page<T> pageByProp(int pageIndex, int pageSize, String param,
			Object value);

	public Page<T> pageByProp(int pageIndex, int pageSize, String param,
			Object value, String order);

	public Page<T> pageByProps(int pageIndex, int pageSize, String[] params,
			Object[] values);

	public Page<T> pageByProps(int pageIndex, int pageSize, String[] params,
			Object[] values, String order);

	public int countByCondition(String[] params, Object[] values);

	/**
	 * 分页查询
	 * 
	 * @param tableName
	 *            表名
	 * @param condition
	 *            查询条件
	 * @param sort
	 *            排序
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
	public List<Map<String, Object>> queryPage(final String tableName,
			final Document condition, final Document sort,
			final Document queryFields, final int pageIndex, final int pageSize);

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
	public long count(final String tableName, final Document condition);

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
	public void updateData(String tableName, Map<String, Object> conditionMap,
			Map<String, Object> dataMap);
}
