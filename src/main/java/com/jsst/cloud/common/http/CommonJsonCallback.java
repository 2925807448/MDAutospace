package com.jsst.cloud.common.http;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jsst.cloud.utils.JsonUtil;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

public class CommonJsonCallback implements Callback {
	protected final String RESP_CODE = "respCode";
	protected final String RESP_CODE_VALUE = "MDAP00000001";
	protected final String RESP_MESSAGE = "respMessage";
	protected final String RESP_DATA = "respData";
	protected final String RESP_IS_SUCCESS = "isSuccess";
	protected final String ERROR_CODE = "MDAP00000002";
	protected final String ERROR_MESSAGE = "失败";
	protected final String EMPTY_CODE = "";
	protected final String EMPTY_MESSAGE = "";
	protected final String NETWORK_ERROR = "";
	protected final String JSON_ERROR = "";
	protected final String OTHER_ERROR = "";
	protected final String COOKIE_STORE = "Set-Cookie";

	private DisposeDataListener disposeDataListener;
	private Class<?> mclass;
	// private Handler handler;
	private Thread thread;

	private static final Logger logger = LoggerFactory.getLogger(CommonJsonCallback.class);

	public CommonJsonCallback(DisposeDataHandler disposeDataHandler) {
		disposeDataListener = disposeDataHandler.disposeDataListener;
		mclass = disposeDataHandler.mclass;
		thread = new Thread();
	}

	@Override
	public void onFailure(Call call, IOException exception) {
		// TODO Auto-generated method stub
		disposeDataListener.onFailure(new OkHttpException(ERROR_CODE, ERROR_MESSAGE));
	}

	@Override
	public void onResponse(Call call, Response response) throws IOException {
		// TODO Auto-generated method stub
		final String result = response.body().string();
		final ArrayList<String> cookieLists = handleCookie(response.headers());
		handleResponse(result);
		if (disposeDataListener instanceof DisposeHandlerCookieListener) {
			((DisposeHandlerCookieListener) disposeDataListener).onCookie(cookieLists);
		}
	}

	private ArrayList<String> handleCookie(Headers headers) {
		ArrayList<String> tempList = new ArrayList<String>();
		for (int i = 0; i < headers.size(); i++) {
			if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
				tempList.add(headers.value(i));
			}
		}
		return tempList;
	}

	private void handleResponse(String result) {
		// TODO Auto-generated method stub
		logger.info("响应结果处理-开始,请求参数:{}", result);
		if (result == null || result.equals("")) {
			System.out.println("1.");
			disposeDataListener.onFailure(new OkHttpException(ERROR_CODE, ERROR_MESSAGE));
			return;
		}
		try {
			System.out.println("2.");
			JSONObject resultObj = new JSONObject(result);
			if (resultObj.has(RESP_CODE)) {
				System.out.println("3.");
				System.out.println("RESP_CODE:"+resultObj.getString(RESP_CODE));
				if (resultObj.getString(RESP_CODE).equals(RESP_CODE_VALUE)) {
					System.out.println("4.");
					if (mclass == null) {
						System.out.println("5.");
						disposeDataListener.onSuccess(resultObj);
					} else {
						Object obj = JsonUtil.parseJsonObjectToModule(resultObj, mclass);
						if (obj != null) {
							System.out.println("6.");
							disposeDataListener.onSuccess(obj);
						} else {
							disposeDataListener.onFailure(new OkHttpException(ERROR_CODE, ERROR_MESSAGE));
						}
					}
				} else {
					if (resultObj.has(ERROR_CODE)) {
						System.out.println("7.");
						disposeDataListener.onFailure(new OkHttpException(resultObj.optString(RESP_CODE),
								resultObj.optString(ERROR_MESSAGE)));
					} else {
						disposeDataListener.onFailure(new OkHttpException(resultObj.optString(RESP_CODE),
								resultObj.optString(EMPTY_MESSAGE)));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			disposeDataListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
			e.printStackTrace();
		}finally {
			logger.info("响应结果处理-结束,请求参数:{}", result);
		}
	}

}
