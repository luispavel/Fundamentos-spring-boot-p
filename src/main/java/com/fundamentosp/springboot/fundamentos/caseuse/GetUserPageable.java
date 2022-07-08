package com.fundamentosp.springboot.fundamentos.caseuse;

import com.fundamentosp.springboot.fundamentos.entity.User;
import com.fundamentosp.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUserPageable {
	private UserService userService;

	public GetUserPageable(UserService userService) {
		this.userService = userService;
	}

	public List<User> getUserPageable(int page, int size) {
		return userService.getUserPageable(page, size);
	}
}
