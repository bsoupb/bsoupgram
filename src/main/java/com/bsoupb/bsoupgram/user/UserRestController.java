package com.bsoupb.bsoupgram.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bsoupb.bsoupgram.user.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		
		int count = userService.addUser(loginId, password, name, email);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@GetMapping("/user/duplicate-id")
	public Map<String, Boolean> isDuplicate(@RequestParam("loginId") String loginId){
		
		int count = userService.isDuplicateUser(loginId);
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		if(count >= 1) {
			resultMap.put("result", true);
		} else {
			resultMap.put("result", false);
		}
		
		return resultMap;
	}
	
}
