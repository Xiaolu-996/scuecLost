package net.xi.news.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/*
     url= doucument.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swagger.show}")
	private boolean swaggerShow;


	//配置swagger的docket的bean实例
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("looni")
				//关闭swagger服务
				.enable(swaggerShow)
				.select()
				//配置要扫描接口的方式
				//basepackage:扫描指定包
				//withClassAnnotation:扫描类上面的注解
				.apis(RequestHandlerSelectors.basePackage("net.xi.news.controller"))
				//过滤什么路径不扫描
				//.paths(PathSelectors.ant("/kaung/**"))
				.build();
	}


	//配置swagger的消息=apiInfo
	private ApiInfo apiInfo() {
		Contact contact = new Contact("looni", "https://looni.ink/", "jianbin.lu@foxmail.com");
		return new ApiInfo(
				"Looni's SwaggerAPI",
				"The first document of swagger",
				"1.0",
				"https://github.com/looniink",
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<>()
		);
	}
}
