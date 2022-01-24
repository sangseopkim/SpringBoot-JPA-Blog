<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>스프링 JPA 시큐리티</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<!-- 
	기존 로그인 방식에서 사용하는 header 방식
 -->
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="/">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
  	<c:choose>
  		<c:when test="${empty sessionScope.principal }"> 
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="/join">회원가입</a></li>
			</ul>
		</c:when>
  		<c:otherwise>
		    <ul class="navbar-nav">
		      <li class="nav-item"><a class="nav-link" href="/board/form">글쓰기</a></li>
		      <li class="nav-item"><a class="nav-link" href="/user/form">정보변경</a></li>
		      <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
		    </ul>
  		</c:otherwise>
  	</c:choose>
  </div>  
</nav>
<br>