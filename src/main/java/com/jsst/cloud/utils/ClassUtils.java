package com.jsst.cloud.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ClassUtils {

	private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);

	private static final String SET_PREFIX = "set";
	// private static final String IS_PREFIX = "is";
	private static final String GET_PREFIX = "get";

	/**
	 * 设置对象的属性值
	 * 
	 * @param t
	 * @param propertyName
	 * @param value
	 * @return T
	 * @author wenjianhai
	 * @date 2019年1月4日
	 * @since JDK 1.8
	 */
	public static <T> T setValue(T t, String propertyName, String value) {

		try {
			Class<?> clazz = t.getClass();

			Field field = clazz.getDeclaredField(propertyName);
			// 属性类型
			String type = field.getGenericType().getTypeName();

			// 获取clazz类型中的propertyName的属性描述器
			PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);

			if (pd != null) {
				// 从属性描述器中获取 set 方法
				Method setMethod = pd.getWriteMethod();

				if ("java.lang.Integer".equalsIgnoreCase(type) || "Integer".equalsIgnoreCase(type)
						|| "int".equalsIgnoreCase(type)) {
					// 调用set方法将传入的value值保存属性中去
					setMethod.invoke(t, new Object[] { Integer.parseInt(value) });
				} else if ("java.lang.Long".equalsIgnoreCase(type) || "Long".equalsIgnoreCase(type)) {
					// 调用set方法将传入的value值保存属性中去
					setMethod.invoke(t, new Object[] { Long.parseLong(value) });
				} else if ("java.lang.Double".equalsIgnoreCase(type) || "Double".equalsIgnoreCase(type)) {
					setMethod.invoke(t, new Object[] { Double.parseDouble(value) });
				} else if ("java.lang.Float".equalsIgnoreCase(type) || "Float".equalsIgnoreCase(type)) {
					setMethod.invoke(t, new Object[] { Float.parseFloat(value) });
				} else if ("java.math.BigDecimal".equalsIgnoreCase(type) || "BigDecimal".equalsIgnoreCase(type)) {
					setMethod.invoke(t, new Object[] { new BigDecimal(value) });
				} else {
					// 调用set方法将传入的value值保存属性中去
					setMethod.invoke(t, new Object[] { value });
				}
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error("设置对象的属性值失败：{}，属性名：{}，值：{}", e.getMessage(), propertyName, value, e);
		} catch (Exception e) {
			logger.error("设置对象的属性值失败：{}，属性名：{}，值：{}", e.getMessage(), propertyName, value, e);
		}

		return t;
	}

	/**
	 * 获取指定属性的值
	 * 
	 * @param t
	 * @param propertyName 属性名
	 * @return Object
	 * @author wenjianhai
	 * @date 2019年1月4日
	 * @since JDK 1.8
	 */
	public static <T> Object getValue(T t, String propertyName) {

		Object value = null;

		try {
			// 获取对象的类型
			Class<?> clazz = t.getClass();

			// 获取clazz类型中的propertyName的属性描述器
			PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);

			if (pd != null) {
				// 从属性描述器中获取 get 方法
				Method getMethod = pd.getReadMethod();
				// 调用方法获取方法的返回值
				value = getMethod.invoke(clazz, new Object[] {});
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error("获取指定属性 {} 的值失败：{}", propertyName, e.getMessage(), e);
		} catch (Exception e) {
			logger.error("获取指定属性 {} 的值失败：{}", propertyName, e.getMessage(), e);
		}

		return value;
	}

	/**
	 * 获取对象中属性对应的字段及其值
	 * 
	 * @param t
	 * @return Map
	 * @author wenjianhai
	 * @date 2019年3月5日
	 * @since JDK 1.8
	 */
	public static <T> Map<String, Object> getColumnValue(T t) {

		Map<String, Object> map = new HashMap<>();

		try {
			// 获取对象的类型
			Class<?> clazz = t.getClass();
			// 对象中的属性
			Field[] fields = clazz.getDeclaredFields();

			for (Field field : fields) {

				field.setAccessible(true);

				if (field.isAnnotationPresent(org.springframework.data.mongodb.core.mapping.Field.class)) {
					/* 解析加有注解Column的对象的属性 */
					org.springframework.data.mongodb.core.mapping.Field anno = field
							.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
					// 字段英文名
					String columnNo = anno.value().toUpperCase();
					// 获取值
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
					Method getMethod = pd.getReadMethod();

					Object object = getMethod.invoke(t);

					map.put(columnNo, object);
				}
			}
			map.put("CREATE_TIME", addHour(8));
			map.put("UPDATE_TIME", addHour(8));

		} catch (Exception e) {
			logger.error("获取对象 {} 中属性对应的字段及其值失败：{}", t, e.getMessage(), e);
		}

		return map;
	}

	private static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName) {
		// 根据需求，定制 自己的get和set方法
		Method setMethod = null;
		Method getMethod = null;
		PropertyDescriptor pd = null;
		try {
			// 根据字段名来获取字段
			Field field = clazz.getDeclaredField(propertyName);

			if (field != null) {
				// 构建方法的后缀
				String methodEnd = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
				setMethod = clazz.getDeclaredMethod(SET_PREFIX + methodEnd, new Class[] { field.getType() });
				// 构建get 方法
				getMethod = clazz.getDeclaredMethod(GET_PREFIX + methodEnd, new Class[] {});
				// 构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中
				pd = new PropertyDescriptor(propertyName, getMethod, setMethod);
			}
		} catch (Exception e) {
			logger.error("获取指定属性 {} 的getter、setter方法失败：" + e.getMessage(), propertyName, e);
		}

		return pd;
	}
	
	private static Date addHour(int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR, hour);// 24小时制

		return cal.getTime();
	}
}

