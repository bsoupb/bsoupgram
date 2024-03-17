package com.bsoupb.bsoupgram.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bsoupb.bsoupgram.post.domain.Post;
import com.bsoupb.bsoupgram.post.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post addPost(String contents, MultipartFile file) {
		
		String imagePath 
		Post post = Post.builder()
					.contents(contents)
					.imagePath(imagePath)
					.build();
		
		return postRepository.save(post);
		
	}
	
}
