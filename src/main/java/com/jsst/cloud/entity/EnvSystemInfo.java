package com.jsst.cloud.entity;

import java.util.Map;

import com.beust.jcommander.internal.Maps;
import com.vimalselvam.testng.SystemInfo;

public class EnvSystemInfo implements SystemInfo{

	@Override
	public Map<String, String> getSystemInfo() {
		// TODO Auto-generated method stub
		Map<String,String> systemInfo=Maps.newHashMap();
		systemInfo.put("环境类型", "测试环境");
		systemInfo.put("测试类型", "接口测试");
		systemInfo.put("测试执行人", "欧亮飞");
		return systemInfo;
	}
	
}
