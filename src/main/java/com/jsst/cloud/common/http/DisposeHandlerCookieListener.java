package com.jsst.cloud.common.http;

import java.util.ArrayList;

public interface DisposeHandlerCookieListener extends DisposeDataListener{
	public void onCookie(ArrayList<String> cookieStrLists);
}
