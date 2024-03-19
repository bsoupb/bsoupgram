package com.bsoupb.bsoupgram.post.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PostDetail {
	
	// 카드 화면을 구성하기 위해 필요한 데이터
	// dto : 데이터를 전달하고 처리하기 위해 사용되는 객체
	
	private int postId;
	private int userId;
	private String userLoginId;
	private String contents;
	private String imagePath;
	
}
