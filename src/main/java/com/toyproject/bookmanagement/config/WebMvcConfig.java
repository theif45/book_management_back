package com.toyproject.bookmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 3000번 포트에서 오는 요청이 있으면 모든 요청에 corsorigin 어노테이션 적용
		registry.addMapping("/**")
				.allowedMethods("*")
				.allowedOrigins("*");
//				.allowedOrigins("http://localhost:3000");
	}
}
