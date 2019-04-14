package com.jsst.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	// 连接信息
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.timeout}")
	private int timeout;
	@Value("${spring.redis.password}")
	private String password;

	// 连接池信息
	@Value("${spring.redis.maxTotal}")
	private int maxTotal;
	@Value("${spring.redis.maxIdle}")
	private int maxIdle;
	@Value("${spring.redis.minIdle}")
	private int minIdle;
	@Value("${spring.redis.maxWaitMillis}")
	private int maxWaitMillis;
	@Value("${spring.redis.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;
	@Value("${spring.redis.numTestsPerEvictionRun}")
	private int numTestsPerEvictionRun;

	@Value("${spring.redis.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;
	@Value("${spring.redis.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${spring.redis.testWhileIdle}")
	private boolean testWhileIdle;

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration rf = new RedisStandaloneConfiguration();
		rf.setHostName(host);
		rf.setPort(port);
		rf.setPassword(RedisPassword.of(password));
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpb = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
				.builder();
		jpb.poolConfig(redisPoolConfig());

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(rf, jpb.build());
		jedisConnectionFactory.afterPropertiesSet();
		return new JedisConnectionFactory(rf, jpb.build());
	}

	@Bean
	public JedisPoolConfig redisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		jedisPoolConfig.setTestWhileIdle(testWhileIdle);
		return jedisPoolConfig;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		// 将刚才的redis连接工厂设置到模板类中
		template.setConnectionFactory(factory);
		template.afterPropertiesSet();
		// string
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		template.setValueSerializer(genericJackson2JsonRedisSerializer);
		// hash
		template.setHashKeySerializer(stringRedisSerializer);
		template.setHashValueSerializer(genericJackson2JsonRedisSerializer);

		return template;
	}
}
