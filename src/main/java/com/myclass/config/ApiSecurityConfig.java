package com.myclass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.myclass.filter.AuthFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.myclass")
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	private UserDetailsService userDetailsService;

	public ApiSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/v2/api-docs",
		"/configuration/ui",
		"/swagger-resources/**",
		"/configuration/security",
		"/swagger-ui.html",
		"/webjars/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/api/admin/**")
		.permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("api/admin/user/**","api/admin/role/**")
		.hasAnyRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("api/admin/course/**")
		.hasAnyRole("ADMIN","TEACHER")
		.and()
		.authorizeRequests()
		.antMatchers("/api/admin/**")
		.authenticated();
		
		http.addFilter(new AuthFilter(authenticationManager(), userDetailsService));
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
