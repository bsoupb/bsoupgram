package com.bsoupb.bsoupgram.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsoupb.bsoupgram.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
