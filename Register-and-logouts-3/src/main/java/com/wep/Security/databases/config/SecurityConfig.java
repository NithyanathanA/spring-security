package com.wep.Security.databases.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getDetails() {
		return new CustemerUserDetaliService();
	}
	
	@Bean
	public DaoAuthenticationProvider  getAuthenticationProvider() {
		DaoAuthenticationProvider daoauthetication=new DaoAuthenticationProvider();
		daoauthetication.setUserDetailsService(getDetails());
		daoauthetication.setPasswordEncoder(passwordEncoder());
		return daoauthetication;
		
	}
	
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
//		
	http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/","/register","/login","/saveuser").permitAll()
		.requestMatchers("/user/**").authenticated().and()
		.formLogin().loginPage("/login").loginProcessingUrl("/userlogin")
	.defaultSuccessUrl("/user/profile").permitAll();
         
	
		return http.build();
		
	}
		
		
		
		
		
		
		
	}


