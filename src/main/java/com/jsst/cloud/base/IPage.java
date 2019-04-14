package com.jsst.cloud.base;

import java.io.Serializable;
import java.util.Collection;

public abstract interface IPage<T> extends Serializable {
	public abstract long getPageTotal();

	public abstract int getPageSize();

	public abstract int getPageIndex();

	public abstract long getTotalCount();

	public abstract Collection<T> getData();
}
