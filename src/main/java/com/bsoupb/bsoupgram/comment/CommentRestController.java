package com.bsoupb.bsoupgram.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bsoupb.bsoupgram.comment.domain.Comment;
import com.bsoupb.bsoupgram.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CommentRestController {

	@Autowired
	private CommentService commentService;
	
	public Map<String, String> createComment(
			@RequestParam("postId") int postId
			, @RequestParam("contents") String contents
			, HttpSession session
			){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Comment comment = commentService.addComment(userId, postId, contents);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(comment != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
		
	}
	
}
