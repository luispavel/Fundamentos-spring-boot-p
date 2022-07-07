package com.fundamentosp.springboot.fundamentos.caseuse;

import com.fundamentosp.springboot.fundamentos.entity.User;
import com.fundamentosp.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
	private UserService userService;

	public UpdateUser(UserService userService) {
		this.userService = userService;
	}

	public User update(Long id, User newUser) {
		return userService.update(id, newUser);
	}
}
