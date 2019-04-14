package com.jsst.cloud.controller;

import com.jsst.cloud.base.BaseResponse;
import com.jsst.cloud.utils.Configure;

public class BaseController {

	protected String getMessage(String resultCode) {
		return Configure.getInstance().getValue(resultCode);
	}

	protected void setResponse(BaseResponse response, String resultCode) {
		response.setRespCode(resultCode);
		response.setRespData(getMessage(resultCode));
	}

	protected void setSuccessResponse(BaseResponse response) {
		response.setRespCode("CASE0000000001");
		response.setSuccess(true);
		response.setRespMessage("成功");
	}

	protected void setFailResponse(BaseResponse response) {
		response.setRespCode("CASE0000000002");
		response.setRespMessage("失败");
		response.setSuccess(false);
	}
}
