package com.jsst.cloud.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		context = ac;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static <T> T getBean(Class<T> tClass) {
		if (context != null) {
			return context.getBean(tClass);
		}
		return null;
	}

	public static <T> T getBean(String name, Class<T> tClass) {
		return context.getBean(name, tClass);
	}
}
