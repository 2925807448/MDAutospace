package com.jsst.cloud.common.http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Component
public class CommomOkHttpClient {
	@Autowired
	OkHttpClient okHttpClient;
	/**
	 * 通过构造好的Request,Callback去发送请求
	 * 
	 * @param request
	 * @param callback
	 */
	public Call get(Request request, DisposeDataHandler disposeDataHandler) {
		Call call = okHttpClient.newCall(request);
		call.enqueue(new CommonJsonCallback(disposeDataHandler));
		return call;
	}

	public Call post(Request request, DisposeDataHandler disposeDataHandler) {
		Call call = okHttpClient.newCall(request);
		call.enqueue(new CommonJsonCallback(disposeDataHandler));
		return call;
	}
}
