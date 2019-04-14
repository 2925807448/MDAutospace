package com.jsst.cloud.provider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;

public class DemoProvider {
	private static final Logger logger = LoggerFactory.getLogger(DemoProvider.class);
	@DataProvider(name = "providerDemoData")
	public static Object[][] providerDemoData(Method method) {
		System.out.println(method.getName());
		Object[][] result = null;
		if (method.getName().equals("testGetInterface")) {
			result = new Object[][] { new Object[] { "86a9bce0-e887-11e8-868d-23e1e07aa73d" } };
		}
		return result;
	}
}
