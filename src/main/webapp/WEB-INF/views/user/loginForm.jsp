<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>

<div class="container">
	<form name="loginForm" id="loginForm" method="post" action="/auth/login">
		<div class="form-group">
			<label for="username">User name:</label> <input type="text" class="form-control" placeholder="Enter User Name" id="username" name="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
		</div>	
		<div class="form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember" value="Y"> 아이디 기억하기</label>
		</div><br />
		<button type="submit" class="btn btn-primary">로그인</button>
	</form>
</div>

<%@ include file="../../layout/footer.jsp"%>
<script src="/js/user.js"></script>
<script>
$(document).ready(function() {
	console.log("loginForm.jsp script >>>");
	$("#loginBtn").on("click", function(){ // 회원가입
		console.log("login btn click event >>>");
		//user.login(); 스프링 시큐리티 사용하면서 기존의 로그인 방식 사용안함. 그래서 주석 처리함
	}); // 가입버튼 클릭 이벤트
});
</script>
</body>
</html>