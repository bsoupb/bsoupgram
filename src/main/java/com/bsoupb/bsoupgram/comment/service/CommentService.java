package com.bsoupb.bsoupgram.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoupb.bsoupgram.comment.domain.Comment;
import com.bsoupb.bsoupgram.comment.dto.CommentDetail;
import com.bsoupb.bsoupgram.comment.repository.CommentRepository;
import com.bsoupb.bsoupgram.user.domain.User;
import com.bsoupb.bsoupgram.user.service.UserService;

@Service
public class CommentService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public Comment addComment(int userId, int postId, String contents){
		
		Comment comment = Comment.builder()
								.userId(userId)
								.postId(postId)
								.contents(contents)
								.build();
		
		return commentRepository.save(comment);
		
	}
	
	public List<CommentDetail> getCommentList(int postId){
		
		List<Comment> commentList = commentRepository.findByPostIdOrderByIdDesc(postId);
		
		List<CommentDetail> commentDetailList = new ArrayList<>();
		for(Comment comment:commentList) {
			
			User user = userService.getUserById(comment.getUserId());
			
			CommentDetail commentDetail = CommentDetail.builder()
													.commentId(comment.getId())
													.postId(comment.getPostId())
													.userId(comment.getUserId())
													.contents(comment.getContents())
													.userLoginId(user.getLoginId())
													.build();
			
			commentDetailList.add(commentDetail);
			
		}
		
		return commentDetailList;
		
	}
	
}
