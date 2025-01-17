package com.zeus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.zeus.common.security.CustomAccessDeniedHandler;
import com.zeus.common.security.CustomLoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("Security Config");
		// 1. csrf 토클 비활성화
		http.csrf().disable();

		// 2. 모든 사이트에 인증이 되면 모두 인가된 상태
		// /board/list는 인증만 되면 가겠다는 뜻
		http.authorizeRequests().requestMatchers("/board/list").permitAll();
		// /board/register는 role이 회원(member)인 사람만 인증.인가 해줌
		http.authorizeRequests().requestMatchers("/board/register").hasRole("MEMBER");

		// /notice/list는 인증만 되면 가겠다는 뜻
		http.authorizeRequests().requestMatchers("/notice/list").permitAll();
		// /notice/register는 role이 어드민(admin)인 사람만 인증.인가 해줌
		http.authorizeRequests().requestMatchers("/notice/register").hasRole("ADMIN");

		// 3. id, password 기존것을 사용하지 않고
		// 우리가 설계한 아이디와 패스워드로 인가 정책을 세워서 제시하겠다
		// 우리가 만든 테이블에 연결 (인증, 인가)
//		http.exceptionHandling().accessDeniedPage("/accessError");
		http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());

		// 4. 아이디나 비밀번호가 잘못되었을 때 화면에 인증이 안됩니다. 다시 입력하시오 (화면)

		// 5. 로그인 기본폼을 사용
//		http.formLogin();
		// 5. 로그인 내가 만든 폼을 사용
		http.formLogin().loginPage("/login");

		// 6. 로그아웃 처리를 위한 URI를 지정하고, 로그아웃한 후에 세션을 무효화 한다.
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);

		return http.build();
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다.
		auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER");

		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
	}

	// CustonAccessDeniedHander를 빈으로 등록한다
	public AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	// CustomLoginSuccessHandler를 빈으로 등록한다.
	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

}
