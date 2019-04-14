package com.jsst.cloud.base;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

@SpringBootTest
public class BaseApplicationTest extends AbstractTestNGSpringContextTests {

	@BeforeSuite
	public void dataSetUp() throws SQLException, IOException,
			ClassNotFoundException {
		// 案例执行前参数维护
		System.out.println("执行数据初始化......");
	}

	// 环境配置
	@BeforeClass
	public void envSetUp() {
		System.out.println("执行环境初始化......");
	}

	// 创建environment.properties并放到allure-results目录下，测试报告展现
	@AfterSuite
	public void createEnvPropertiesForReport() throws IOException {
		Map<String, String> data = new HashMap<>();
		data.put("环境类型", "测试环境");
		data.put("测试类型", "接口测试");
		data.put("测试执行人", "欧亮飞");
	}

	@AfterSuite
	public void dataTearDown() throws SQLException, IOException,
			ClassNotFoundException {
		// 案例执行结束后，对数据池的数据进行清理（删除或更新状态）
		// 案例执行后数据清理
		System.out.println("执行数据清理......");
	}
}
