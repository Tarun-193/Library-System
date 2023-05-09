package com.springboot.first.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.first.dto.FindUserIdAndNames;
import com.springboot.first.model.Users;
import com.springboot.first.repository.UsersRepository;
import com.springboot.first.service.UsersService;

@Service
public class UsersServiceImplementation implements UsersService {

	private UsersRepository usersRepository;

	public UsersServiceImplementation(UsersRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}

	@Override
	public void saveUsers(Users users) {
		Optional<Users> existingUser = usersRepository.findByEmail(users.getEmail());
		if (existingUser.isPresent()) {
			throw new IllegalArgumentException("User already exists in database!");
		} else {
			usersRepository.generateUserPassword(users);
			usersRepository.save(users);
		}
	}

	@Override
	public List<FindUserIdAndNames> getIdandName() {
		return usersRepository.findIdandName();

	}

}
