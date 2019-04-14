package com.jsst.cloud.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jsst.cloud.common.redis.*;

@Component
public class RedisManagerSerice {
	private static final Logger logger = LoggerFactory
			.getLogger(RedisManagerSerice.class);

	@Autowired
	private RedisClient redisClient = null;

	public void saveCaseToRedis(Map<String, Object> data) throws Exception {
		logger.info("保存用例到redis,data:{}", data.toString());
		long start = System.currentTimeMillis();
		String key = RedisKeyFormat.getKey(RedisKeyCons.CASE_NO_INFO,
				new Object[] { data.get("caseNo") });
		logger.info("保存用例到redis,key:{}", key);
		redisClient.hmset(key, data);
		logger.info("key：{} 保存到redis成功", key);
		logger.info("未执行的测试用例保存到redis成功,耗时:{} 毫秒",
				Long.valueOf(System.currentTimeMillis() - start));
	}

	public void deleteCaseFromRedis(String caseNo) throws Exception {
		logger.info("删除用例,caseNo:{}", caseNo);
		String key = RedisKeyFormat.getKey(RedisKeyCons.CASE_NO_INFO,new Object[] { caseNo });
		logger.info("删除用例,redis,key:{}", key);
		redisClient.hdel(key, new Object[] { caseNo });
		logger.info("key:{},删除redis成功", key, caseNo);
	}

	public void saveDataToRedis(String key, int time, String value)
			throws Exception {
		logger.info("保存用例到redis,key:{},time:{},value:", key, time, value);
		this.redisClient.set(key, value, time);
		logger.info("key：{} 保存到redis成功", key);
	}

	public Map<Object, Object> getCaseFromRedis(String caseNo) throws Exception {
		logger.info("查询用例信息redis,caseNo:{}", caseNo);
		String key = RedisKeyFormat.getKey(RedisKeyCons.CASE_NO_INFO,new Object[] { caseNo });
		logger.info("查询用例信息redis,key:{}", key);
		return this.redisClient.hmget(key);
	}
}
