package com.cos.blog.api;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	//private static final Logger log = LoggerFactory.getLogger(UserApiController.class);
	private Logger log = LoggerFactory.getLogger(this.getClass());
		
	// @Autowired private HttpSession session;
	
	@Autowired private UserService userService;
	
	// 회원가입
	//
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> userJoin(@RequestBody User user) {
		user.setRole(RoleType.USER);
		userService.userJoin(user);		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1, "회원 가입 완료"); // 공통 DTO 클래스를 통해 json형태로 반환해 준다
	}

	/*
	@PostMapping("/api/user/login") // 전통적인 로그인 방식
	public ResponseDto<Integer> userLogin(@RequestBody User user, HttpSession session) {
		log.debug("유저 로그인 시작");
		User principal = userService.userLogin(user);
		if(principal != null)
		{
			session.setAttribute("principal", principal); // 세션 저장
			log.debug("유저 로그인 완료 : " + principal.getUsername());		
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1, "로그인 완료");
		}
		else
		{
			return new ResponseDto<Integer>(HttpStatus.OK.value(), -1, "로그인 실패");
		}
	} */
	
	/* ResponseDto 사용예제
	@PostMapping("/api/user2")
	public ResponseDto<String> userSave2(@RequestBody User user) {
		return new ResponseDto<String>(HttpStatus.OK.value(), "json data : 숫자, 문자, json구조체가 온다", "사용자 정의 메시지");
	} */
	
}
