package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 공통사용 DTO : https://youtu.be/809058DGUPo?list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm 참조
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
	int status; // HttpStatus 상태
	T data; // 1성공, -1 실패, row 데이터가 있는 경우 결과 데이터
	String msg; // 응답(성공,실패) 메세지
}

/*
사용예제
@PostMapping("/api/user")
public ResponseDto<String> userSave(@RequestBody User user) {
	return new ResponseDto<String>("Y", "정상 수신됨"); == > public class ResponseDto<T> { String res; T msg; }
	return new ResponseDto<Integer>(200, "정상수신"); == > public class ResponseDto<T> { int code; T msg; }
	return new ResponseDto<String>("Y", 200, "정상 수신됨"); == > public class ResponseDto<T> { String res; int code; T msg; }
}
*/