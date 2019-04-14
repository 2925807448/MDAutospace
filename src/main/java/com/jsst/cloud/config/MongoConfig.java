package com.jsst.cloud.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.jsst.cloud.base.BigDecimalToDecimal128Converter;
import com.jsst.cloud.base.Decimal128ToBigDecimalConverter;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoConfig {

	private static final Logger logger = LoggerFactory
			.getLogger(MongoConfig.class);

	@Value("${spring.data.mongodb.enable}")
	private boolean enable;
	@Value("${spring.data.mongodb.database}")
	private String database;
	@Value("${spring.data.mongodb.uri}")
	private String uri;
	@Value("${spring.data.mongodb.cluster}")
	private String cluster;
	@Value("${spring.data.mongodb.username}")
	private String userName;
	@Value("${spring.data.mongodb.password}")
	private String password;
	@Value("${spring.data.mongodb.connectionsPerHost}")
	private int connectionsPerHost;
	@Value("${spring.data.mongodb.threadsAllowedToBlockForConnectionMultiplier}")
	private int threadsAllowedToBlockForConnectionMultiplier;
	@Value("${spring.data.mongodb.maxWaitTime}")
	private int maxWaitTime;
	@Value("${spring.data.mongodb.connectTimeout}")
	private int connectTimeout;
	@Value("${spring.data.mongodb.maxConnectionIdleTime}")
	private int maxConnectionIdleTime;
	@Value("${spring.data.mongodb.secondRead}")
	private boolean secondRead;
	@Value("${spring.data.mongodb.maxStaleness}")
	private int maxStaleness;
	@Value("${spring.data.mongodb.serverSelectionTimeout}")
	private int serverSelectionTimeout;

	@Bean
	public MongoClient getMongoClient() {

		logger.info("获取 MongoClient");

		// 记录参数日志
		logParams();

		try {
			MongoClientOptions.Builder builder = MongoClientOptions.builder();
			// 与目标数据库能够建立的最大connection数量为50
			builder.connectionsPerHost(connectionsPerHost);
			// 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
			builder.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier);
			builder.maxWaitTime(maxWaitTime);
			// 与数据库建立连接的timeout设置为1分钟
			builder.connectTimeout(connectTimeout);
			builder.maxConnectionIdleTime(maxConnectionIdleTime);
			builder.serverSelectionTimeout(serverSelectionTimeout);
			// builder.readPreference(ReadPreference.secondaryPreferred());
			// 添加BigDecimal类型解析
			// builder.codecRegistry(CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(new
			// MongoBigCodec()),
			// MongoClient.getDefaultCodecRegistry()));
			MongoClientURI connnecString = new MongoClientURI(uri);
			return new MongoClient(connnecString);
		} catch (Exception e) {
			logger.error("获取MongoClient失败:" + e.getMessage(), e);
		}
		return null;
	}

	@Bean
	public MappingMongoConverter mappingMongoConverter() {
		DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(
				this.mongoFactory());
		MappingMongoConverter converter = new MappingMongoConverter(
				dbRefResolver, this.mongoMappingContext());
		List<Object> list = new ArrayList<>();
		list.add(new BigDecimalToDecimal128Converter());// 自定义的类型转换器
		list.add(new Decimal128ToBigDecimalConverter());// 自定义的类型转换器
		converter.setCustomConversions(new MongoCustomConversions(list));
		return converter;
	}

	@Bean
	public MongoDbFactory mongoFactory() {

		logger.info("获取 MongoDbFactory");

		MongoClient mongoClient = this.getMongoClient();

		if (mongoClient != null) {
			return new SimpleMongoDbFactory(mongoClient, database);
		}

		logger.error("MongoClient为空");

		return null;
	}

	@Bean
	public MongoMappingContext mongoMappingContext() {
		MongoMappingContext mappingContext = new MongoMappingContext();
		return mappingContext;
	}

	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
			MongoMappingContext context) {

		logger.info("获取 MongoTemplate");

		if (mongoDbFactory != null) {

			DbRefResolver dbRefResolver = new DefaultDbRefResolver(
					mongoDbFactory);
			MappingMongoConverter converter = new MappingMongoConverter(
					dbRefResolver, context);
			converter.setTypeMapper(new DefaultMongoTypeMapper(null));

			converter.afterPropertiesSet();

			return new MongoTemplate(mongoDbFactory,
					this.mappingMongoConverter());
		}

		logger.error("MongoDbFactory为空");

		return null;
	}

	@Bean
	public MongoDatabase getMongoDatabase(MongoClient mongoClient) {

		logger.info("获取 MongoDatabase");

		if (mongoClient != null) {
			return mongoClient.getDatabase(database);
		}

		logger.error("MongoClient为空");

		return null;
	}

	private void logParams() {

		StringBuffer buf = new StringBuffer();
		buf.append("enable=").append(enable).append(", ");
		buf.append("database=").append(database).append(", ");
		buf.append("uri=").append(uri).append(", ");
		buf.append("cluster=").append(cluster).append(", ");
		buf.append("userName=").append(userName).append(", ");
		buf.append("password=").append(password).append(", ");
		buf.append("connectionsPerHost=").append(connectionsPerHost)
				.append(", ");
		buf.append("threadsAllowedToBlockForConnectionMultiplier=")
				.append(threadsAllowedToBlockForConnectionMultiplier)
				.append(", ");
		buf.append("maxWaitTime=").append(maxWaitTime).append(", ");
		buf.append("connectTimeout=").append(connectTimeout).append(", ");
		buf.append("maxConnectionIdleTime=").append(maxConnectionIdleTime)
				.append(", ");
		buf.append("secondRead=").append(secondRead).append(", ");
		buf.append("maxStaleness=").append(maxStaleness);

		logger.info("MongDB配置参数为：{}", buf.toString());
	}
}
