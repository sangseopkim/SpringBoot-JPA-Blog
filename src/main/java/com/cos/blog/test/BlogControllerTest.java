package com.cos.blog.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogControllerTest {
	
	private static final Logger log = LoggerFactory.getLogger(BlogControllerTest.class);
	
	@GetMapping("/test/hello")
	public String hello() {
		log.debug("log : BlogControllerTest");
		this.test();
		return "<h1>hello Spring boot 3</h1><br />DB TEST..  수정3-3";
		// 아 어렵다..
		// 그래도 해야지.. ㅋㅋㅋ
	}
	
	// root / apmsetup , db : blog
	public void test() {
		System.out.println("this is test function");
	}

	public void myName() {
		System.out.println("this is myName function");
	}
	
	// test ... 
	
	// 수정
}
