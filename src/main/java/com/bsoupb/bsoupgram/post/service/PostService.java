package com.bsoupb.bsoupgram.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bsoupb.bsoupgram.comment.dto.CommentDetail;
import com.bsoupb.bsoupgram.comment.service.CommentService;
import com.bsoupb.bsoupgram.common.FileManager;
import com.bsoupb.bsoupgram.like.service.LikeService;
import com.bsoupb.bsoupgram.post.domain.Post;
import com.bsoupb.bsoupgram.post.dto.PostDetail;
import com.bsoupb.bsoupgram.post.repository.PostRepository;
import com.bsoupb.bsoupgram.user.domain.User;
import com.bsoupb.bsoupgram.user.service.UserService;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private CommentService commentService;
	
	public Post addPost(int userId, String contents, MultipartFile file) {
		
		String filePath = FileManager.saveFile(userId, file);
		Post post = Post.builder()
					.userId(userId)
					.contents(contents)
					.imagePath(filePath) 
					.build();
		
		return postRepository.save(post);
		
	}
	
	// 등록 내림 차순으로 조회된 결과 돌려주는 기능
	public List<PostDetail> getPostList(int loginUserId){
		List<Post> postList = postRepository.findAllByOrderByIdDesc();
		
		List<PostDetail> postDetailList = new ArrayList<>();
		
		
		for(Post post:postList) {
			
			int userId = post.getUserId();
			User user = userService.getUserById(userId);
			
			// 좋아요 개수 조회
			int likeCount = likeService.getLikeCount(post.getId());
			
			// 로그인한 사용자가 좋아요 했는지 여부 조회
			boolean isLike = likeService.isLike(loginUserId, post.getId());
			
			// 댓글 
			List<CommentDetail> commentList = commentService.getCommentList(post.getId());

			PostDetail postDetail = PostDetail.builder()
									.postId(post.getId())
									.userId(userId)
									.userLoginId(user.getLoginId())
									.contents(post.getContents())
									.imagePath(post.getImagePath())
									.likeCount(likeCount)
									.isLike(isLike)
									.commentList(commentList)
									.build();
			
			postDetailList.add(postDetail);
			
			
		}
		return postDetailList;
	}
	
	public Post deletePost(int id) {
		
		Optional<Post> optionalPost = postRepository.findById(id);
		Post post = optionalPost.orElse(null);
		
		if(post != null) {
			
			// 게시글 좋아요 데이터 삭제
			likeService.deleteLikeByPostId(id);
			
			// 게시글 댓글 데이터 삭제
			commentService.deleteCommentByPostId(id);
			
			FileManager.removeFile(post.getImagePath());
			postRepository.delete(post);
			
		}
		
		return post;
		
	}
 	
}
