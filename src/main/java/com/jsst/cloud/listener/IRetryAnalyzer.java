package com.jsst.cloud.listener;

import org.testng.ITestResult;

public interface IRetryAnalyzer {
	public boolean retry(ITestResult result);
}
