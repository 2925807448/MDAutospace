package com.jsst.cloud.utils;

import com.alibaba.fastjson.JSON;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanUtil {
	private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

	public static Map<String, Object> transBean2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), obj
					.getClass().getSuperclass());

			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (!key.equals("class")) {
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj, new Object[0]);
					if (value != null) {
						map.put(key, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	private static String getterMethodName(String properties) {
		if (properties != null) {
			StringBuffer sb = new StringBuffer();

			sb.append("get").append(properties);
			sb.setCharAt(3, Character.toUpperCase(sb.charAt(3)));

			return sb.toString();
		}
		return null;
	}

	public static void transMap2Bean(Map<String, Object> map, Object obj)
			throws IntrospectionException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (map.containsKey(key)) {
				Object value = map.get(key);

				Method setter = property.getWriteMethod();
				logger.debug("给{}，赋值{}，map的key为{}", new Object[] { setter,
						value, key });
				try {
					setter.invoke(obj, new Object[] { value });
				} catch (IllegalArgumentException ex) {
					value = StringUtil.objToString(value);
					setter.invoke(obj, new Object[] { value });
				}
			}
		}
	}

	public static <T> T transMap2BeanSupportUnderline(Map<String, Object> map,
			Class<T> clazz) {
		String json = JSON.toJSONString(map);
		return JSON.parseObject(json, clazz);
	}

	public static <E> String getListBeanField(List<E> list, String fieldName,
			String separator) {
		if (ContainerUtil.listIsEmpty(list)) {
			return null;
		}
		StringBuffer fields = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			BeanInfo beanInfo = null;
			try {
				beanInfo = Introspector.getBeanInfo(obj.getClass(), obj
						.getClass().getSuperclass());

				PropertyDescriptor[] propertyDescriptors = beanInfo
						.getPropertyDescriptors();
				for (PropertyDescriptor property : propertyDescriptors) {
					String key = property.getName();
					if (fieldName.equals(key)) {
						Method getter = property.getReadMethod();
						fields.append(getter.invoke(obj, new Object[0]));
						break;
					}
				}
			} catch (Exception e) {
				logger.info("获取列表对象中的成员变量列表转换对象失败，bennInfo：{}", beanInfo);
				continue;
			}
			if (i < list.size() - 1) {
				fields.append(separator);
			}
		}
		return fields.toString();
	}

	/**
	 * 
	 * @param bean
	 * @param fieldNames
	 * @return 返回A=xxx&B=yyy&C=zzz
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IntrospectionException
	 */
	public static String buildUrl(Object bean, String fieldNames)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IntrospectionException {
		if ((bean == null) || (StringUtil.isEmpty(fieldNames))) {
			return null;
		}
		String[] fieldNameArray = fieldNames.split(",");

		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < fieldNameArray.length; i++) {
			String fieldName = fieldNameArray[i];
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (fieldName.equals(key)) {
					Method getter = property.getReadMethod();
					stringBuffer.append(fieldName + "=");
					stringBuffer.append(StringUtil.objToStringWithDef(
							getter.invoke(bean, new Object[0]), ""));
					if (i < fieldNameArray.length - 1) {
						stringBuffer.append("&");
					}
				}
			}
		}
		return stringBuffer.toString();
	}

	public static Map<String, Object> beanToMap(Object obj) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return map;
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : pds) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") != 0) {
				Method getter = property.getReadMethod();
				Object value = getter != null ? getter.invoke(obj,
						new Object[0]) : null;
				map.put(key, value);
			}
		}
		return map;
	}
}
