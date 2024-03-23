<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타임라인</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="d-flex justify-content-center container">
		<!-- 전체 layout -->
		<div class="container">
			<!-- 입력 상자 -->
			<div class="container post border rounded">
				<div>
					<textarea class="form-control border-0" rows="4" id="contentsInput"></textarea>
				</div>
				<div class="d-flex justify-content-between pt-3">
					<i class="bi bi-file-image" style="font-size:25px !important;" id="imageIcon"></i>
					<input id="imgUpload" type="file" class="d-none">
					<button type="button" class="btn btn-primary btn-sm" id="uploadBtn">업로드</button>
				</div>
			</div>
			<!-- /입력 상자 -->
			
			<!-- 타임라인 -->
			<div class="timeline">
				
				<c:forEach var="post" items="${postList }">
				<!-- 카드 -->
				<div class="container post-box">
					<div class="d-flex justify-content-between" style="margin:10px;">
						<div class="font-25">${post.userLoginId }</div>
						<i class="bi bi-three-dots font-25 dot-icon" data-post-id="${post.postId }"></i>
					</div>
					<div>
						<img src="${post.imagePath }" class="img-responsive">
					</div>
					<div class="p-2">
						<c:choose>
							<c:when test="${post.like }">
								<i class="bi bi-heart-fill text-danger font-25 like-icon" data-post-id="${post.postId }"></i> 좋아요 ${post.likeCount }개
							</c:when>
							<c:otherwise>
								<i class="bi bi-heart font-25 like-icon" data-post-id="${post.postId }"></i> 좋아요 ${post.likeCount }개
							</c:otherwise>
						</c:choose>
						
					</div>
					<div class="post">
						${post.contents }
					</div>
					<hr>
					<!-- 댓글 목록 -->
					<div class="comment-box">	
						<c:forEach var="comment" items="${post.commentList }">
						<div class="d-flex comment">
							<label class="col-1">${comment.userLoginId }</label>
							<div>${comment.contents }</div>
						</div>
						</c:forEach>
						<div class="d-flex">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="댓글을 입력해 주세요" id="contentsInput${post.postId }">
								<div class="input-group-append">
									<button class="btn btn-primary comment-btn" data-post-id="${post.postId }" type="button">입력</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /카드 -->
				</c:forEach>
				
			</div>
			<!-- /타임라인 -->
		</div>
		<!-- /전체 layout -->
		</section>
		<hr>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>

<script>
	
	$(document).ready(function(){
		
		$(".comment-btn").on("click", function(){
			
			let postId = $(this).data("post-id");
			
			// 클릭 이벤트가 발생한 버튼 태그 객체
			// post-id
			// 버튼 태그 옆에 있는 태그
			 let contents = $("#contentsInput" + postId).val();
			// let comments = $(this).prev().val();
			
			$.ajax({
				type:"post"
				, url:"/post/comment/create"
				, data:{"postId":postId, "contents":contents}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					} else{
						alert("댓글 실패");
					}
				}
				, error:function(){
					alert("댓글 에러");
				}
			});
			
		});
		
		$(".dot-icon").on("click", function(){
			
			let postId = $(this).data("post-id");
			
			.ajax({
				type:"delete"
				, url:"/post/delete"
				, data:{"id":postId};
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					} else{
						alert("삭제 실패");
					}
				};
				, error:function(){
					alert("삭제 에러");
				};
			});
			
			
		});
		
		$(".like-icon").on("click", function(){
			
			// 이벤트가 발생한 태그 객체
			// data-post-id
			let postId = $(this).data("post-id");
			
			$.ajax({
				type:"post"
				, url:"/post/like"
				, data:{"postId":postId}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					} else {
						alert("좋아요 실패")
					}
				}
				, error:function(){
					alert("좋아요 에러")
				}
			});
			
		});
		
		$("#imageIcon").on("click", function(){
			
			$("#imgUpload").click();
			
		});
		
		$("#uploadBtn").on("click", function(){
			
			let contents = $("#contentsInput").val();
			let file = $("#imgUpload")[0].files[0];
			
			if(contents == ""){
				alert("내용을 입력해 주세요");
				return;
			}
				
			// 파일에 대한 유효성 검사
			if(file == null){
				alert("파일을 선택해 주세요");
				return;
			}
			
			let formData = new FormData();
			formData.append("contents", contents);
			formData.append("imageFile", file);
			
			$.ajax({
				type:"post"
				, url:"/post/create"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false
				, contentType:false
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					} else {
						alert("글 쓰기 실패");
					}
						
				}
				, error:function(){
					alert("글 쓰기 에러");
				}
			});
			
			
			
		});
		
	});

</script>



</body>
</html>