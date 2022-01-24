<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>

<div class="container">
	<form name="joinForm" id="joinForm">
		<div class="form-group">
			<label for="username">User name:</label> <input type="text" class="form-control" placeholder="Enter User Name" id="username" name="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
		</div>		
		<div class="form-group">
			<label for="email">Email address:</label> <input type="email" class="form-control" placeholder="Enter email" id="email" name="email">
		</div>
		<!--<div class="form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> 동의하기
			</label>
		</div> -->
	</form>
	<button type="button" id="joinFormSaveBtn" class="btn btn-primary">회원가입</button>	
</div>

<%@ include file="../../layout/footer.jsp"%>
<script src="/js/user.js"></script>
</body>
</html>