package com.jsst.cloud.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlConfig {
	private static final Logger logger = LoggerFactory
			.getLogger(Configure.class);

	private static SqlConfig sqlConfig = null;

	private static Properties properties = null;
	private List<String> acceptableSuffix;
	private long maxSize = 0L;

	private SqlConfig() {
		properties = new Properties();
		Properties systemProperties = getProperties("/sql/cloud_sql.xml");
		if (systemProperties != null) {
			properties.putAll(systemProperties);
		}
	}

	private Properties getProperties(String fileName) {
		InputStream is = null;
		Properties properties = null;
		try {
			is = getClass().getResourceAsStream(fileName);
			properties = new Properties();
			properties.loadFromXML(is);
		} catch (Exception e) {
			logger.error("初始化配置文件错误!", e);
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

	public static SqlConfig getInstance() {
		if (sqlConfig == null) {
			synchronized (Configure.class) {
				if (sqlConfig == null) {
					sqlConfig = new SqlConfig();
				}
			}
		}
		return sqlConfig;
	}

	public static String getResString(String key) {
		return SqlConfig.getInstance().getValue(key).trim();
	}

	public static void main(String[] args) {
		System.out.println(SqlConfig.getResString("NISSP_CLOUD_QUERY_CARD"));
	}
}
