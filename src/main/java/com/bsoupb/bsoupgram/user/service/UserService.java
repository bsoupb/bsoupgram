package com.bsoupb.bsoupgram.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoupb.bsoupgram.common.EncryptUtils;
import com.bsoupb.bsoupgram.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public int addUser(String loginId, String password, String name, String email) {
		String encryptPassword = EncryptUtils.md5(password);
		return userRepository.insertUser(loginId, encryptPassword, name, email);
	}
	
	public int isDuplicateUser(String loginId) {
		return userRepository.isDuplicateUser(loginId);
	}
}
