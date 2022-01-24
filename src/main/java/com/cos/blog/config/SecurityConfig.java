package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

// 참조 : https://bamdule.tistory.com/53


@Configuration // 빈등록  : 스프링 컨테이너에서 객체를 관리(IoC)
@EnableWebSecurity // 시큐리티 필터가 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정주소로 접근을 하면 권한 및 인증을 미리 체크 한다
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() //요청 request가 들어올때
				.antMatchers("/auth/**", "/test/**").permitAll()
				//.antMatchers("/dummy/**", "/").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/auth/login") // 인증안된 페이지에서 접근시 로그인 페이지로  변경
		;
	}
}
