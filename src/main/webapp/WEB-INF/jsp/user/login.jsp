<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	
	<div id="wrap">
		<section class="main">
			<div class="container my-5">
				<h1 class="text-center" style="padding:40px;">bsoupgram</h1>
					<div class="container d-flex justify-content-center align-items-center">
						<div>
						<form id="loginForm">
							<div class="pt-3">
								<input type="text" id="idInput" class="form-control" style="width:350px;" placeholder="아이디">
							</div>
							<div class="pt-3">
								<input type="password" id="passwordInput" class="form-control" style="width:350px;" placeholder="비밀번호">
							</div>
							<div class="pt-3">
								<button type="submit" id="loginBtn" class="form-control btn btn-primary" style="width:350px;">로그인</button>
							</div>
						</form>	
							<div class="box d-flex justify-content-center align-items-center">
								<div>계정이 없으신가요?</div>
								<a href="/user/join-view">회원가입</a>
							</div> 
						</div>
					</div>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>

<script>
	$(document).ready(function(){
		$("#loginForm").on("submit", function(e){
			// submit 이벤트가 가진 페이지 이동 기능 취소
			// 이벤트가 가진 기본 기능을 취소한다
			
			e.preventDefault();
			
			let id = $("#idInput").val();
			let password = $("#passwordInput").val();
			
			if(id == ""){
				alert("아이디를 입력해 주세요.")
				$("#idInput").focus();
				return;
			}
			
			if(password == ""){
				alert("비밀번호를 입력해 주세요.")
				$("#passwordInput").focus();
				return;
			}
			
			$.ajax({
				type:"post"
				, url:"/user/login"
				, data:{"loginId":id, "password":password}
				, success:function(data){
					if(data.result == "success"){
						location.href="/post/timeline-view"
					} else{
						alert("로그인 실패")
					}
				}
				, error:function(){
					alert("로그인 에러")
				}
			});

			
			
		});
	});
</script>

</body>
</html>