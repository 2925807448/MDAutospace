package com.jsst.cloud.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.jsst.cloud.base.BaseTestNGApplication;
import com.jsst.cloud.provider.DemoProvider;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Feature("测试用例模型")
public class TestCaseDemo extends BaseTestNGApplication {

	@Story("测试用例模型")
	@Test(dataProvider = "providerDemoData", dataProviderClass = DemoProvider.class, description = "接口模型")
	public void testGetInterface(String interfaceId) {
		System.out.println(interfaceId);
		Assert.assertEquals("86a9bce0-e887-11e8-868d-23e1e07aa73d", interfaceId);
	}
}
