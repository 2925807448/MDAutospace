package com.jsst.cloud.base;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public class Page<T> implements IPage<T> {
	private static final long serialVersionUID = 8107580997134495482L;
	private int pageSize;
	private int pageIndex;
	private long pageTotal;
	private long totalCount;
	private Collection<T> data;

	public Page() {
	}

	public Page(Collection<T> data, long totalCount, int pageIndex, int pageSize) {
		this.data = (data == null ? new ArrayList(0) : data);
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.pageIndex = (pageIndex <= 0 ? 1 : pageIndex);
		this.pageTotal = ((totalCount - 1L) / pageSize + 1L);
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public long getPageTotal() {
		return this.pageTotal;
	}

	public int getPageIndex() {
		return this.pageIndex;
	}

	public Collection<T> getData() {
		return this.data;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();
		Method[] methods = getClass().getMethods();
		boolean isFirst = true;
		int i = 0;
		for (int n = methods.length; i < n; i++) {
			try {
				Method method = methods[i];
				if (((method.getModifiers() & 0x1) == 1)
						&& (method.getDeclaringClass() != Object.class)
						&& ((method.getParameterTypes() == null) || (method
								.getParameterTypes().length == 0))) {
					String methodName = method.getName();
					String property = null;
					if (methodName.startsWith("get")) {
						property = methodName.substring(3, 4).toLowerCase()
								+ methodName.substring(4);
					} else if (methodName.startsWith("is")) {
						property = methodName.substring(2, 3).toLowerCase()
								+ methodName.substring(3);
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
