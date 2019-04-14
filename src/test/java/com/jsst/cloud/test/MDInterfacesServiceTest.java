package com.jsst.cloud.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Title;

import com.jsst.cloud.MdAutospaceApplication;
import com.jsst.cloud.base.BaseApplicationTest;
import com.jsst.cloud.entity.MDInterfacesInfo;
import com.jsst.cloud.service.IMDInterfacesService;
import com.jsst.cloud.utils.JsonUtil;

@SpringBootTest(classes = { MdAutospaceApplication.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class MDInterfacesServiceTest extends BaseApplicationTest {

	@Autowired
	private IMDInterfacesService interfacesService;
	
	@Title("查询测试接口地址")
	@Parameters({ "interfaceId_01"})
	@Test(groups = "seccondGroup")
	public void queryInterfaceDetail(String interfaceId) {
		MDInterfacesInfo mdf = interfacesService
				.queryInterfacesDetail(interfaceId);
		System.out.println(JsonUtil.toJSON(mdf));
	}
}
