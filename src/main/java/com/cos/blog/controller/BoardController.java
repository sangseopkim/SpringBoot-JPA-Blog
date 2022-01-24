package com.cos.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.test.BlogControllerTest;

@Controller
public class BoardController {
	
	private static final Logger log = LoggerFactory.getLogger(BlogControllerTest.class);
	
	//http://localhost:8080/blog/
	@GetMapping({"", "/"})
	public String index() {
		log.debug("page : http://localhost:8080/");
		return "index";
	}
}
