package com.cos.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// @Service 어노테이션을 선언하여 스프링의 컴포넌트 스캔을 통해 Bean으로 등록됨[IoC를 한다]
@Service
public class UserService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired // 의존성 주입 DI
	private UserRepository userRepository;
	
	@Transactional // 정합성을 유지하기 위해 @Transactional을 붙힌다
	public void userJoin(User user) {
		userRepository.save(user);
	}

	 /*/ select라 트랜젝션이 필요없지만 데이터 정합성을 위해 
	// 어노테이션을 아래처럼 선언하므로서 select할때 트랜잭션을 시작하고 서비스 종료시 트랜잭션 종료한다
	@Transactional(readOnly = true) 
	public User userLogin(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}*/
	
}
