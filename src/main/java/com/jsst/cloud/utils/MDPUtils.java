package com.jsst.cloud.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

public class MDPUtils {

	/**
	 * 根据指定配置读取文件
	 * 
	 * @param cofig
	 * @return
	 */
	public static Properties getConfigProperty(String config) {
		Properties configProp = new Properties();
		InputStream input = MDPUtils.class.getClassLoader()
				.getResourceAsStream(config);
		try {
			configProp.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configProp;
	}

	public static String getValueByKey(String filepath, String key) {
		Properties pros = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filepath));
			pros.load(in);
			String value = pros.getProperty(key);
			System.out.println(key + ":" + value);
			return value;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void getAllProperties(String filepath) throws IOException {
		Properties pros = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filepath));
		pros.load(in);
		Enumeration en = pros.propertyNames();
		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = pros.getProperty(strKey);
			System.out.println(strKey + "=" + strValue);
		}
	}

	public static void writeProperties(String filepath, String strKey,
			String strValue) throws IOException {
		Properties pros = new Properties();
		InputStream in = new FileInputStream(filepath);
		pros.load(in);
		OutputStream out = new FileOutputStream(filepath);
		pros.setProperty(strKey, strValue);
		pros.store(out, "Update" + strKey + "name");
	}

	public static Properties getConfigureObject(String config) {
		Properties props = null;
		try {
			String path = MDPUtils.class.getClass().getResource("/").getPath();
			path = path.substring(1, path.indexOf("classes"));
			System.out.println(path + config);
			props.load(new FileInputStream(path + config));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}

	public Properties getProperties(String fileName) {
		InputStream is = null;
		Properties properties = null;
		try {
			is = getClass().getResourceAsStream(fileName);
			properties = new Properties();
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
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

	public static void main(String[] args) {
		Properties pros = MDPUtils
				.getConfigProperty("config/allure.properties");
		System.out.println(pros.get("allure.link.tms.pattern"));
	}

}
