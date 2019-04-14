package com.jsst.cloud.test;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Title;

import com.jsst.cloud.MdAutospaceApplication;
import com.jsst.cloud.base.BaseApplicationTest;
import com.jsst.cloud.entity.MDInterfacesCase;
import com.jsst.cloud.service.IMDInterfacesCaseService;
import com.jsst.cloud.service.impl.RedisManagerSerice;
import com.jsst.cloud.utils.BeanUtil;
import com.jsst.cloud.utils.CaseCodeGenerator;

@SpringBootTest(classes = { MdAutospaceApplication.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class MDInterfacesCaseServiceCaseTest extends BaseApplicationTest {
	String case_no = null;

	@BeforeClass(description = "测试环境参数")
	public void setUp() {
		this.case_no = CaseCodeGenerator.generateCaseNo();
	}

	@Autowired
	private IMDInterfacesCaseService interfacesService;

	@Autowired
	private RedisManagerSerice redisManagerSerice;

	@Title("新增用例测试")
	@Test
	public void testAddInterfacesCase() throws Exception {
		MDInterfacesCase interfaceInput = new MDInterfacesCase();
		interfaceInput.setCaseNo(this.case_no);
		interfaceInput.setCaseName("积分账户注册接口");
		interfaceInput.setCaseDesc("积分账户注册接口");
		interfaceInput.setInterfaceId("86a9bce0-e887-11e8-868d-23e1e07aa73d");
		interfaceInput.setGroupId("5bed56c392d4a36e2fc7a864");
		interfaceInput.setProjectId("5becda6092d4a36e2fc7a811");
		interfaceInput.setModuleId("5bed62a292d4a36e2fc7a885");
		interfaceInput.setMethodName("GET");
		interfaceInput.setRedisCheck(false);
		interfaceInput.setTableCheck(false);
		interfaceInput.setExpectOutput("CS000000001");
		interfaceInput.setCreateTime(new Date());
		interfaceInput.setReqUrl("/member-center/account/register");
		interfaceInput.setVersion("V1.0.0");
		interfaceInput.setIsRun("Y");
		interfacesService.saveInterfacesCase(interfaceInput);
		this.redisManagerSerice.saveCaseToRedis(BeanUtil
				.beanToMap(interfaceInput));
	}

	@Title("查询用例测试")
	@Test
	public void testQeuryCaseFromRedis() throws Exception {
		MDInterfacesCase mdc = interfacesService
				.queryInterfaceCaseByNo(this.case_no);
		Map<Object, Object> maps = this.redisManagerSerice.getCaseFromRedis(case_no);
		System.out.println(maps.toString());
	}
	
	@Title("删除用例测试")
	@Test
	public void testDeleteCaseFromRedis() throws Exception {
		MDInterfacesCase mdc = interfacesService
				.queryInterfaceCaseByNo(this.case_no);
		this.redisManagerSerice.deleteCaseFromRedis(case_no);
	}
}
