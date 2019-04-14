package com.jsst.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.jsst.cloud.controller")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				// 页面标题
				.title("[case-executor用例执行器]-RESTful API")
				// 创建人
				.contact(new Contact("ouliangfei", "http://localhost:9003/case-executor/swagger-ui.html", ""))
				// 版本号
				.version("1.0")
				// 描述
				.description("【case-executor用例执行器v1.0接口规范】").build();
	}
}
