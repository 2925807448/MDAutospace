package com.jsst.cloud.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configure {
	private static final Logger logger = LoggerFactory
			.getLogger(Configure.class);

	private static Configure configure = null;

	private static Properties properties = null;
	private List<String> acceptableSuffix;
	private long maxSize = 0L;

	private Configure() {
		properties = new Properties();
		Properties configProperties = getProperties("/config/config.properties");
		Properties allureProperties = getProperties("/config/allure.properties");
		Properties envProperties = getProperties("/config/environment.properties");
		if (configProperties != null) {
			properties.putAll(configProperties);
		}
		if (allureProperties != null) {
			properties.putAll(allureProperties);
		}
		if (envProperties != null) {
			properties.putAll(envProperties);
		}
	}

	private Properties getProperties(String fileName) {
		InputStream is = null;
		Properties properties = null;
		try {
			is = getClass().getResourceAsStream(fileName);
			properties = new Properties();
			properties.load(is);
		} catch (Exception e) {
			throw new RuntimeException(
					"Could not read default properties file:" + fileName);
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

	public static Configure getInstance() {
		if (configure == null) {
			synchronized (Configure.class) {
				if (configure == null) {
					configure = new Configure();
				}
			}
		}
		return configure;
	}
	public static String getResString(String key) {
        return Configure.getInstance().getValue(key).trim();
    }
	public static void main(String[] args) {
		System.out.println(Configure.getResString("allure.link.tms.pattern"));
	}
}
