package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogControllerTest {
	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello Spring boot 3</h1><br />DB TEST..  수정3-3";
		// 아 어렵다..
		// 그래도 해야지.. ㅋㅋㅋ
	}
	
	// root / apmsetup , db : blog
	
	public void test() {
		// test method
		// begin
	}
	
	
}
