package com.jsst.cloud.base;

import java.lang.reflect.Method;

public class BaseEntity implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3039111511275211988L;

	public String toString() {
		StringBuilder buf = new StringBuilder();
		Method[] methods = getClass().getMethods();
		boolean isFirst = true;
		int i = 0;
		for (int n = methods.length; i < n; i++) {
			try {
				Method method = methods[i];
				if (((method.getModifiers() & 0x1) == 1) && (method.getDeclaringClass() != Object.class)
						&& ((method.getParameterTypes() == null) || (method.getParameterTypes().length == 0))) {
					String methodName = method.getName();
					String property = null;
					if (methodName.startsWith("get")) {
						property = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
					} else if (methodName.startsWith("is")) {
						property = methodName.substring(2, 3).toLowerCase() + methodName.substring(3);
					}
					if (property != null) {
						Object value = method.invoke(this, new Object[0]);
						if (isFirst) {
							isFirst = false;
						} else {
							buf.append(",");
						}
						buf.append(property);
						buf.append(":");
						if ((value instanceof String)) {
							buf.append("\"");
						}
						buf.append(value);
						if ((value instanceof String)) {
							buf.append("\"");
						}
					}
				}
			} catch (Exception e) {
			}
		}
		return "{" + buf.toString() + "}";
	}
}
