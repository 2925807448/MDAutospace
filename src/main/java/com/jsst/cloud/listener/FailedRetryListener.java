package com.jsst.cloud.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

public class FailedRetryListener implements IAnnotationTransformer {
	public void transform(ITestAnnotation iTestAnnotation, Class aClass,
			Constructor constructor, Method method) {
		{
			IRetryAnalyzer retry = iTestAnnotation.getRetryAnalyzer();
			if (retry == null) {
				iTestAnnotation.setRetryAnalyzer(FailedRetry.class);
			}
		}
	}
}
