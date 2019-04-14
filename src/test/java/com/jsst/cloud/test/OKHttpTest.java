package com.jsst.cloud.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testng.annotations.Test;

import com.jsst.cloud.MdAutospaceApplication;
import com.jsst.cloud.base.BaseApplicationTest;
import com.jsst.cloud.common.http.CommomOkHttpClient;
import com.jsst.cloud.common.http.CommonRequest;
import com.jsst.cloud.common.http.DisposeDataHandler;
import com.jsst.cloud.common.http.DisposeDataListener;
import com.jsst.cloud.common.http.RequestParams;

@SpringBootTest(classes = { MdAutospaceApplication.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class OKHttpTest extends BaseApplicationTest {
	
	@Autowired
	CommomOkHttpClient commomOkHttpClient;
	/*
	public void testGetRequest() {
		final Request request = new Request.Builder()
				.url("http://localhost:7300/mock/5c99a2ab53989b131c33fd23/case-executor/getUserInfo").build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response resonpse) throws IOException {
				// TODO Auto-generated method stub
				final String result = resonpse.body().string();
				System.out.println(result);
			}

			@Override
			public void onFailure(Call call, IOException resonpse) {
				// TODO Auto-generated method stub
				System.out.println("124342343");
			}
		});
	}
	
	
	public void testPostRquest() {
		FormBody.Builder formBody = new FormBody.Builder();
		formBody.add("username", "13632842844");
		formBody.add("password", "o111111");
		Request request = new Request.Builder()
				.url("http://localhost:7300/mock/5c99a2ab53989b131c33fd23/case-executor/login").post(formBody.build())
				.build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// TODO Auto-generated method stub
				final String result = response.body().string();
				System.out.println(result);
			}

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				// TODO Auto-generated method stub

			}
		});
	}
	*/
	
	@Test
	public void testOKGetRequest() {
		commomOkHttpClient.get(CommonRequest
				.createGetRequest("http://localhost:7300/mock/5c99a2ab53989b131c33fd23/case-executor/getUserInfo", null),
				new DisposeDataHandler(new DisposeDataListener() {

					@Override
					public void onSuccess(Object response) {
						// TODO Auto-generated method stub
						System.out.println("success result:"+response.toString());
					}

					@Override
					public void onFailure(Object response) {
						// TODO Auto-generated method stub
						System.out.println("failure result:"+response.toString());
					}
				}));
	}
	
	@Test
	public void testPostRequest() {
		RequestParams params=new RequestParams();
		params.put("username", "13632842844");
		params.put("password", "o111111");
		commomOkHttpClient.post(CommonRequest.createPostRequest("http://localhost:7300/mock/5c99a2ab53989b131c33fd23/case-executor/login", params), new DisposeDataHandler(new DisposeDataListener() {
			
			@Override
			public void onSuccess(Object response) {
				// TODO Auto-generated method stub
				System.out.println("result:"+response.toString());
			}
			
			@Override
			public void onFailure(Object response) {
				// TODO Auto-generated method stub
				System.out.println("result:"+response.toString());
			}
		}));
	}
}
