package com.jsst.cloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsst.cloud.base.BaseResponse;
import com.jsst.cloud.entity.MDInterfacesInfo;
import com.jsst.cloud.service.IMDInterfacesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "请求接口API", description = "MDInterfacesController", tags = "请求接口控制层")
public class MDInterfacesController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MDInterfacesController.class);

	@Autowired
	IMDInterfacesService interfacesService;

	@ApiOperation(value = "根据id查询接口详情", tags = { "获取接口详情" }, notes = "根据id查询接口详情")
	@ResponseBody
	@PostMapping("/queryInterfacesDetail")
	public BaseResponse queryInterfacesDetail(@RequestParam("interfaceId") @ApiParam("传入字符串数据") String interfaceId) {
		logger.info("controller层查询请求接口详情-开始，请求参数:{}", interfaceId);
		BaseResponse response = new BaseResponse();
		try {
			MDInterfacesInfo rest = interfacesService.queryInterfacesDetail(interfaceId);
			response.setRespData(rest);
			setSuccessResponse(response);
		} catch (Exception e) {
			// TODO: handle exception
			setFailResponse(response);
		} finally {
			logger.info("controller层查询请求接口详情-结束，返回参数:{}", response);
		}
		return response;
	}
}
