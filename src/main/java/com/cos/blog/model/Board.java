package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// https://getinthere.tistory.com/23 참조

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY) 
		private int id;
		
		@Column(nullable = false, length=100)		
		private String title;

		@Lob // 대용량 데이터
		private String content; // 섬머노트 라이브러리	
		
		
		//private int userId; // 관계형으로 설정된 ID FK=> 자바는 오브젝트
		@ManyToOne(fetch = FetchType.EAGER) // Many =Board, One = User, 연관관계를 만듬 , FetchType.EAGER 전략 => 조회시 유저 정보를 같이 가져온다 / LAZY 전략은 안가져온다
		//@ManyToOne(fetch = FetchType.LAZY) // 조회시 유저정보를 가져오지 않는다
		@JoinColumn(name="userId")
		private User user;
	 
		// .게시글 호출시 답변글도 한번에 조회 할 수 있도록 한다(Select할때 조인하여 결과가 가져온다)
		// 참조영상 : https://www.youtube.com/watch?v=DtMmXQl4_hw&list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm&index=22
		@OneToMany(mappedBy = "board", fetch= FetchType.EAGER) 
		// mappedBy 연관관계의 주인이 아님(FK가 아님)으로 설정한다는 뜻, Table에 컬럼 안만듬!, 
		// 따옴표의 변수명은 Reply 클래스에서 외래키로 지정한 Board 클래스의 변수명과 동일해야 한다 boardId가 아님!
		// fetch= FetchType.LAZY => fetch 전략은 필요할때만 데이터를 가져온다
		// fetch = FetchType.EAGER => 조회될때 연관된 테이블(Reply)도 무조건 가져온다 => 여기서는 이걸 사용
		//@JoinColumn(name="replyId") ==> 이건 필요없다
		private List<Reply> reply;
		
		
		@ColumnDefault("0") // 기본값
		private int count; // 조회수
		
		@CreationTimestamp // 시간이 자동 입력
		private Timestamp createDate;		
		
}
