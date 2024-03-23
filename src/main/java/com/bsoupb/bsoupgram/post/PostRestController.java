package com.bsoupb.bsoupgram.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bsoupb.bsoupgram.post.domain.Post;
import com.bsoupb.bsoupgram.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
public class PostRestController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/post/create")
	public Map<String, String> createPost(
			@RequestParam("contents") String contents
			, @RequestParam("imageFile") MultipartFile imageFile
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Post post = postService.addPost(userId, contents, imageFile);
		
		Map<String, String> resultMap = new HashMap<>();
		if(post != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	@DeleteMapping("/post/delete")
	public Map<String, String> deletePost(@RequestParam("id") int id){
		
		Post post = postService.deletePost(id);
		
		Map<String, String> resultMap = new HashMap<>();
		if(post != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
