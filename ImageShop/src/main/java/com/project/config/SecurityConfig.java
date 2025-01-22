package com.project.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.project.common.security.CustomAccessDeniedHandler;
import com.project.common.security.CustomLoginSuccessHandler;
import com.project.common.security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// 0. 환경설정 
@Configuration
// 1. 시큐리티 설정 
@EnableWebSecurity
// 2. 시큐리티 애너테이션 활성화를 위한 설정  
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
	// 2. 데이터 정보 주입
	@Autowired
	DataSource dataSource;

	// 3. 시큐리티 설정 관리하는 함수 
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 4. csrf 토큰 비활성화
		http.csrf().disable();

		// 우리는 인가설정 이거 안쓸거임
		// URI 패턴으로 접근 제한을 설정한다.
		// 5. 인가설정( 모든 팀원이 알아서 결정)
		// 가장 앞에 http.authorizeHttpRequests().이거쓰고 .으로만 이어도 됨 길어서 줄바꿈한거임
//		http.authorizeHttpRequests().requestMatchers("/board/**").authenticated();
//		http.authorizeHttpRequests().requestMatchers("/manager/**").hasRole("MANAGER");
//		http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
//		http.authorizeHttpRequests().anyRequest().permitAll();

		// 6. 개발자가 정의한 로그인 페이지의 URI를 지정하고 로그인 성공 후 처리를 담당하는 핸들러 콜한다
//		http.formLogin();
		// CustomLoginSuccessHandler를 로그인 성공 처리자로 지정한다.
		http.formLogin().loginPage("/auth/login").loginProcessingUrl("/login")
				.successHandler(createAuthenticationSuccessHandler());
//		http.formLogin().loginPage("/login").successHandler(createAuthenticationSuccessHandler());

		// 7. 로그아웃 처리를 위한 URI를 지정하고, 로그아웃한 후에 세션을 무효화 한다.
		http.logout().logoutUrl("/auth/logout").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSION_ID");

		// 8. 로그인 실패시 등록한 CustomAccessDeniedHandler를 접근 거부 처리자로 지정한다.
		http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());

		// 9. 데이터 소스를 지정하고 테이블을 이용해서 기존 로그인 정보를 기록
		// 쿠키의 유효 시간을 지정한다(24시간).
		http.rememberMe().key("zeus").tokenRepository(createJDBCRepository()).tokenValiditySeconds(60 * 60 * 24);

		return http.build();

	}

	// 1. read me 
	private PersistentTokenRepository createJDBCRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	// 2.데이터 베이스 통한 회원 관리
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(createUserDetailsService()).passwordEncoder(createPasswordEncoder());
	}

	// 3. 스프링 시큐리티의 UserDetailsService를 구현한 클래스를 빈으로 등록한다.
	@Bean
	public UserDetailsService createUserDetailsService() {
		return new CustomUserDetailsService();
	}

//	// 4. 사용자가 정의한 비번 암호화 처리기를 빈으로 등록한다.
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 5. 로그인 잘못됐을 때 발생되는 핸들러처리 CustomAccessDeniedHandler를 빈으로 등록한다.
	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	// 6. 로그인 성공했을 때 발생되는 핸들러처리
	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	

}