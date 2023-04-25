package com.toyproject.bookmanagement.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.toyproject.bookmanagement.security.jwt.JwtAuthenticationEntryPoint;
import com.toyproject.bookmanagement.security.jwt.JwtAuthenticationFilter;
import com.toyproject.bookmanagement.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		http.httpBasic().disable();
		http.formLogin().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
			.antMatchers("/auth/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint);
	}

}
