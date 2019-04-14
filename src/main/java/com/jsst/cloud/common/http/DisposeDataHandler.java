package com.jsst.cloud.common.http;

public class DisposeDataHandler {
	public DisposeDataListener disposeDataListener=null;
	public Class<?> mclass=null;
	public String mSource;
	public DisposeDataHandler(DisposeDataListener disposeDataListener) {
		this.disposeDataListener = disposeDataListener;
	}
	public DisposeDataHandler(DisposeDataListener disposeDataListener, Class<?> mclass) {
		this.disposeDataListener = disposeDataListener;
		this.mclass = mclass;
	}
	public DisposeDataHandler(DisposeDataListener disposeDataListener, String mSource) {
		this.disposeDataListener = disposeDataListener;
		this.mSource = mSource;
	}
}
