package net.xi.news.config;


import net.xi.news.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//拦截器
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/admin/**")
				.excludePathPatterns("/admin")
				.excludePathPatterns("/admin/login");
	}
}
