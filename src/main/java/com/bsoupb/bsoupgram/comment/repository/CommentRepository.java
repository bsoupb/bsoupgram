package com.bsoupb.bsoupgram.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsoupb.bsoupgram.comment.domain.Comment;


public interface CommentRepository extends JpaRepository<Comment, Integer>{

	// SELECT * FROM `comment` WHERE `postId` = #{} ORDER BY `id` DESC;
	public List<Comment> findByPostIdOrderByIdDesc(int postId);
	
}
