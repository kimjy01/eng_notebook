package com.spring.notebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.notebook.service.PrincipalOauth2UserService;
import com.spring.notebook.service.UserDetailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

	private final UserDetailService userService;
	private final PrincipalOauth2UserService principalOauth2UserService;
	
	// 시큐리티 기능 비활성화
	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring()
				.requestMatchers("/static/**", "/error");
	}
	
	// 특정 HTTP 요청에 대한 웹 기반 보안 구성
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				// 인증, 인가 설정
				.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
						.requestMatchers("/login").permitAll()
						.anyRequest().authenticated())
				
			    // 로그아웃 설정
			    .logout(logout -> {
			        logout
			            .logoutSuccessUrl("/login")
			            .invalidateHttpSession(true)
			            .deleteCookies("JSESSIONID");
			    })
			    
			    // oauth2 login
			    .oauth2Login(oauth2Login -> oauth2Login
		    	    .loginPage("/login")
		    	    .defaultSuccessUrl("/update", true)
		    	    .userInfoEndpoint(userInfo -> userInfo
		    	        .userService(principalOauth2UserService)
		    	    )
				)
			    
			    // csrf 비활성화
			    .csrf(csrf -> csrf.disable())

			    .build();
	}
	
	// 인증 관리자 관련 설정
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailService userDetailService) throws Exception {
		
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userService)
				.passwordEncoder(bCryptPasswordEncoder)
				.and()
				.build();
	}
}
