package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

// https://youtu.be/qYxWWWVpJkA?list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm
// 에러가 발생할 경우 에러를 캐치하여 리턴한다
@ControllerAdvice // Exception 발생시 모든 클래스에서 연결하도록 설정
@RestController
public class GlobalExceptionHandler {
	
	/*
	@ExceptionHandler(value=IllegalArgumentException.class) // IllegalArgumentException이 발생하면 이 함수에 전달
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
	*/
	
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<Integer> AllException(Exception e) {
		return new ResponseDto<Integer>(HttpStatus.INTERNAL_SERVER_ERROR.value(), -1, e.getMessage());
	}
	
}
