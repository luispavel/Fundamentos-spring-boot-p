package com.fundamentosp.springboot.fundamentos.caseuse;

import com.fundamentosp.springboot.fundamentos.entity.User;
import com.fundamentosp.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
	private UserService userService;

	public CreateUser(UserService userService) {
		this.userService = userService;
	}

	public User save(User newUser) {
		return UserService.save(newUser);
	}
}

