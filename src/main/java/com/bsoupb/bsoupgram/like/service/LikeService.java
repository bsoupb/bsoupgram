package com.bsoupb.bsoupgram.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoupb.bsoupgram.like.domain.Like;
import com.bsoupb.bsoupgram.like.repository.LikeRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepository;
	
	public Like addLike(int postId, int userId){
		
		Like like = Like.builder()
						.postId(postId)
						.userId(userId)
						.build();
		
		return likeRepository.save(like);
		
		
		
	}
	
	
}
