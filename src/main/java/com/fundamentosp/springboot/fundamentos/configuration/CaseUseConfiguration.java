package com.fundamentosp.springboot.fundamentos.configuration;

import com.fundamentosp.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosp.springboot.fundamentos.caseuse.GetUserImplement;
import com.fundamentosp.springboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {
	@Bean
	GetUser getUser(UserService userService) {
		return new GetUserImplement(userService);
	}
}
