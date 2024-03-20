package com.bsoupb.bsoupgram.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bsoupb.bsoupgram.like.domain.Like;
import com.bsoupb.bsoupgram.like.service.LikeService;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeService likeService;
	
	@PostMapping("/post/like")
	public Map<String, String> like(
			@RequestParam("postId") int postId
			,HttpSession session 
			){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Like like = likeService.addLike(postId, userId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(like != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
