package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO같은 자동으로 bean 등록이 된다
// @Repository // 생략가능
public interface UserRepository extends JpaRepository<User, Integer > {
	
	/*
	// JPA Naming 전략
	//User findByUsernameAndPassword(String username, String password);
	// => select * from where username=? and password=?
	@Query(value="select * from where username=? and password=?", nativeQuery = true)
	User login(String username, String password);
	*/
}
