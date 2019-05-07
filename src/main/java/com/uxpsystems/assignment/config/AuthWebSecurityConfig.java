package com.uxpsystems.assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.uxpsystems.assignment.service.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class AuthWebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomAuthenticationProvider customAuthProvider;
	
	@Autowired
	CustomAuthenticationEntryPoint entry;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();

		http.csrf().disable().

				antMatcher("/Assignment/**").httpBasic().authenticationEntryPoint(entry).and().exceptionHandling()
				.authenticationEntryPoint(entry)

				.and()

				.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()

				.antMatchers("/Assignment/**")

				.authenticated();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.authenticationProvider(customAuthProvider);
	}

}
