package com.student.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	//All URLs are protected
	//A login form is shown for unauthorized requests
	//CSRF disable
	//Frames

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.httpBasic();
				//.and()
				//.authorizeRequests()
				//.antMatchers("/students/").permitAll()
				//.and()
				//.authorizeRequests()
				//.anyRequest().authenticated().and().headers().frameOptions().disable();

		return http.build();
	}

}