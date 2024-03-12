<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
							<div class="input-group pt-3">
								<input type="text" id="idInput" class="form-control" placeholder="아이디">
								<div class="input-group-append">
									<button class="btn btn-secondary" type="button" id="isDuplicateBtn">중복확인</button>
								</div>
							</div>
							<div class="text-primary small d-none pt-1" id="duplicateText">
								중복된 아이디입니다.						
							</div>
							<div class="text-primary small d-none pt-1" id="availableText">
								사용할 수 있는 아이디입니다.						
							</div>
							<div class="pt-3">
								<input type="password" id="passwordInput" class="form-control" style="width:350px;" placeholder="비밀번호">
							</div>
							<div class="pt-3">
								<input type="password" id="passwordConfirmInput" class="form-control" style="width:350px;" placeholder="비밀번호 확인">
							</div>
							<div class="pt-3">
								<input type="text" id="nameInput" class="form-control" style="width:350px;" placeholder="이름">
							</div>
							<div class="pt-3">
								<input type="text" id="emailInput" class="form-control" style="width:350px;" placeholder="이메일">
							</div>
							<div class="pt-3">
								<button type="button" id="joinBtn" class="form-control btn btn-primary" style="width:350px;">로그인</button>
							</div>
							<div class="box d-flex justify-content-center align-items-center">
								<div>계정이 있으신가요?</div>
								<a href="/user/login-view">로그인</a>
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
		
		// 중복확인 체크 여부
		var isDuplicateCheck = false;
		
		// id 중복 여부
		var isDuplicateId = true;
		
		$("#isDuplicateBtn").on("click", function(){
			
			var id = $("#idInput").val();
			
			if(id == ""){
				alert("아이디를 입력해 주세요.");
				$("idInput").focus();
				return;
			}
			
			$.ajax({
				type:"get"
				, url:"/user/duplicate-id"
				, data:{"loginId":id}
				, success:function(data){
					
					// 중복확인 체크
					isDuplicateCheck = true;
					isDuplicateId = data.isDuplicate;
					
					if(data.isDuplicate){
						$("#duplicateText").removeClass("d-none");
						$("#availableText").addClass("d-none");
					} else{
						$("#availableText").removeClass("d-none");
						$("#duplicateText").addClass("d-none");
					}
				}
				, error:function(){
					alert("에러")
				}
			});
			
		});
		
		$("#joinBtn").on("click", function(){
			
			var id = $("#idInput").val();
			var password = $("#passwordInput").val();
			var passwordConfirm = $("#passwordConfirmInput").val();
			var name = $("#nameInput").val();
			var email = $("#emailInput").val();
			
			if(id == ""){
				alert("아이디를 입력해 주세요.");
				$("#idInput").focus();
				return;
			}
			
			// id 중복확인 안한 경우
			if(!isDuplicateCheck){
				alert("아이디 중복 확인을 해주세요");
				return;
			}
			
			// id 중복된 경우 
			if(isDuplicateId){
				alert("아이디가 중복되었습니다.");
				return;
			}
			
			if(password == ""){
				alert("비밀번호를 입력해 주세요.");
				$("#passwordInput").focus();
				return;
			}
			
			if(password != passwordConfirm){
				alert("비밀번호를 확인해 주세요.");
				$("#passwordConfirmInput").focus();
				return;
			}
			
			if(name == ""){
				alert("이름을 입력해 주세요.");
				$("#nameInput").focus();
				return;
			}
			
			if(email == ""){
				alert("이메일을 입력해 주세요.");
				$("#emailInput").focus();
				return;
			}
			
			$.ajax({
				type:"post"
				, url:"/user/join"
				, data:{"loginId":id, "password":password, "name":name, "email":email}
				, success:function(data){
					if(data.result == "success"){
						location.href = "/user/login-view"
					} else {
						alert("회원가입 실패");	
					}
				}
				, error:function(){
					alert("회원가입 에러")
				}
			});
			
		});
		
	});

</script>

</body>
</html>