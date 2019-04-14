package com.jsst.cloud.utils;

import org.springframework.core.env.Environment;

public class ValueUtil {
	public static String get(String key) {
		String value = "";

		Environment environment = (Environment) ApplicationContextProvider
				.getBean(Environment.class);
		try {
			value = environment.getProperty(key);
		} catch (Exception e) {
		}
		return value;
	}
}
