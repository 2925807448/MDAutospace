package com.jsst.cloud.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jsst.cloud.base.BaseTestNGApplication;

import ru.yandex.qatools.allure.annotations.Title;

@Listeners
public class IdeTest extends BaseTestNGApplication {
	
	@BeforeMethod
    public void setup() throws Exception {
		System.out.println("初始化数据");
    }

    @Title("创建节点，运行，提交，测试运行")
    @Test
    public void nodeTest() {
          
    }
}
