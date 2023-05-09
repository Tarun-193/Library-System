package com.springboot.first.service;

import java.util.List;

import com.springboot.first.dto.FindUserIdAndNames;
import com.springboot.first.model.Users;

public interface UsersService {
	void saveUsers(Users users);
	List<FindUserIdAndNames> getIdandName();
	
}
