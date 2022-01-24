package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// https://getinthere.tistory.com/23?category=884180

// ORM -> Java Object를 테이블로 매핑해주는 기술

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //  @Entity가 있으면 User 클래스가 MySql 테이블에 생성된다
//@DynamicInsert // Insert시 null인 필드를 제외시켜 준다(role 항목에 적용) => 썩 좋은 방법은 아니라고 함
public class User {
	@Id // primary 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length=30, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length=100)
	private String password;

	@Column(nullable = false, length=50)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;  // Timestamp 외에 LocalDateTime(https://java119.tistory.com/52)
	
}



