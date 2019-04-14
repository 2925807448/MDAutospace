package com.jsst.cloud.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailedRetry implements IRetryAnalyzer {
	private int retryCount = 1;
	private static final int maxRetryCount = 2;

	public boolean retry(ITestResult iTestResult) {
		// 抛出异常则重跑失败案例
		if (iTestResult.getThrowable() instanceof Exception
				&& retryCount % maxRetryCount != 0) {
			retryCount++;
			return true;
		} else {
			retryCount = 1;
			return false;
		}
	}
}
