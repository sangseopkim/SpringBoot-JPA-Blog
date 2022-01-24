package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입 DI
	private UserRepository userRepository;
	
	@PostMapping("/dummy/jointest") // application/x-www-form-urlencoded : 일반 POST 방식으로 전송
	public String jointest(String username, String password, String email) { // 스프링에서 자동으로 변수 매핑
		return "회원가입 완료 테스트 : " + username + ", " + email;
	}
	
	@PostMapping("/dummy/join") 
	public String userJoin(User user) { // 스프링에서 자동으로 User 클래스에 매핑
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		System.out.println("createDate : " + user.getCreateDate());
		return "회원가입 완료 : " + user.getUsername() + ", " + user.getEmail()+ ", " + user.getPassword();
	}

	@PostMapping("/dummy/ajax") 
	public String AjaxTest1(@RequestBody User user) {
		return "Post 전송 완료 : " + user.getUsername() + ", " + user.getEmail()+ ", " + user.getPassword();
	}
	@PutMapping("/dummy/ajax") 
	public String AjaxTest2(@RequestBody User user) {
		return "Put 전송 완료 : " + user.getUsername() + ", " + user.getEmail()+ ", " + user.getPassword();
	}
	@DeleteMapping("/dummy/ajax") 
	public String AjaxTest3(@RequestBody User user) {
		return "Delete 전송 완료 : " + user.getUsername() + ", " + user.getEmail()+ ", " + user.getPassword();
	}
	@PatchMapping("/dummy/ajax") 
	public String AjaxTest4(@RequestBody User user) {
		return "Patch 전송 완료 : " + user.getUsername() + ", " + user.getEmail()+ ", " + user.getPassword();
	}
	
	
	// 스프링부트에서 제공하는 페이징 가져오기
	// http://localhost:8080/blog/dummy/user/page  => 1페이지
	// http://localhost:8080/blog/dummy/user/page?page=1  => 2페이지
	@GetMapping("/dummy/user/page")
	public List<User> userPageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUsers = userRepository.findAll(pageable); // => 회원정보와 페이징 정보 포함
		
		if(pagingUsers.isFirst()) {
			// 첫번째 회원인 경우 분기점
		}
		
		if(pagingUsers.isLast()) {
			// 마지막 회원인 경우 분기점
		}
		
		List<User> users = pagingUsers.getContent();  // 회원 정보만 가져오기
		return users;
	}	
	
	@GetMapping("/dummy/user")
	public List<User> userList() {
		List<User> user = userRepository.findAll();
		return user;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User userDetail(@PathVariable int id) {
		// null인 경우를 대비해서 Optional로 User객체를 담아서 가져온다
		// 방식1 User user = userRepository.findById(id).get(); ==> null인경우 에러 발생
		/* 방식2
		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() { // 결과가 없는 경우 빈객체를 리턴
				return new User();
			}
		}); */
		
		// 방식3
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " +id);
			}
		});
		
		/*/ 방식4 람다식
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 존재안함(람다식). id : " +id);
		});*/
		
		
		return user;
		
		// 방식4
		//Optional<User> user = userRepository.findById(id);
		
		
		//System.out.println(user.get().toString());
		//return user.get();
	}
	
	// 수정 방식1 : userRepository.save 사용
	@PutMapping("/dummy/user_old/{id}")
	public User userUpdateOld(@PathVariable int id, @RequestBody User requestUser) {
		
		User user = userRepository.findById(id).get(); // 기존 정보
		
		System.out.println("requestUser email : "+requestUser.getEmail());
		System.out.println("requestUser password : "+requestUser.getPassword());		

		user.setEmail(requestUser.getEmail()); // 수정할 정보만 업데이트
		user.setPassword(requestUser.getPassword());
		userRepository.save(user);

		System.out.println("update email : "+user.getEmail());
		System.out.println("update password : "+user.getPassword());
		return user;
	}

	// 수정 방식2 : userRepository.save 사용안함, 
	// 더티 체킹 사용 : JPA에서는 트랜잭션이 끝나는 시점에 변화가 있는 모든 엔티티 객체를 데이터베이스에 자동으로 반영
	@Transactional // 함수 종료시 자동 commit된다
	@PutMapping("/dummy/user/{id}")
	public User userUpdate(@PathVariable int id, @RequestBody User requestUser) {

		//System.out.println("requestUser email : "+requestUser.getEmail());
		//System.out.println("requestUser password : "+requestUser.getPassword());
		
		User user = userRepository.findById(id).orElseThrow(()->{ // 림다 표현식
			 return new IllegalArgumentException("수정실패");
		}); // ** 영속화된 user 오브젝트
		user.setEmail(requestUser.getEmail()); // ** 영속화 된 오브젝트를 비교해 변경된을 수정
		user.setPassword(requestUser.getPassword());	
		
		return user;
	} // ** 트랜젝션 종료시 변경이 있으면 커밋이 실행
	/*
	 * 영속성 컨텍스트 : https://youtu.be/dDsI1J4QC6g?list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm
	 */
	
	@DeleteMapping("dummy/user/{id}")
	public String userDelete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e ) {
			return "삭제 실패. id " +id + " / " + e.getMessage();
		}
		return "삭제되었습니다. id " +id;
	}
	
	
}
