package com.bsoupb.bsoupgram.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoupb.bsoupgram.post.dto.PostDetail;
import com.bsoupb.bsoupgram.post.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/timeline-view")
	public String timeline(Model model) {
		
		List<PostDetail> postList = postService.getPostList();
		
		model.addAttribute("postList", postList);
		
		return "/post/timeline";
	}
	
}
