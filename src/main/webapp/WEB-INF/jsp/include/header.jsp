<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="d-flex justify-content-between align-items-center mt-3">
	<h1>bsoupgram</h1>
	
	<c:if test="${not empty userId }" >
		<div class="mr-3">${userLoginId }  <a href="/user/logout">로그아웃</a> </div>
	</c:if>
</header>