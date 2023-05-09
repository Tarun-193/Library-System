package com.springboot.first.contoller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.first.dto.FindUserIdAndNames;
import com.springboot.first.service.UsersService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UsersService usersService;

	public UsersController(UsersService usersService) {
		super();
		this.usersService = usersService;
	}


	@GetMapping
	public List<FindUserIdAndNames> getUsers() {
		return usersService.getIdandName();
	}

}
