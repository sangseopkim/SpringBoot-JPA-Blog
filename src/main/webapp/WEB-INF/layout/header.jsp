<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!--  https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" /><!-- 현재 사용자를 나타내는 주요 개체에 대한 직접 액세스 허용 -->
</sec:authorize>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>스프링 JPA 시큐리티</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<c:choose>
		<c:when test="${!empty principal }"> 
			<!-- 로그인 된 경우 -->
			<h1>${principal}</h1>
		</c:when>
	</c:choose>	

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<%  // https://www.baeldung.com/spring-security-taglibs %>
			<sec:authorize access="!isAuthenticated()">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/auth/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/join">회원가입</a></li>
				</ul>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/board/form">글쓰기</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/form">정보변경</a></li>
					<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
				</ul>
			</sec:authorize>
		</div>
	</nav>
	<br>