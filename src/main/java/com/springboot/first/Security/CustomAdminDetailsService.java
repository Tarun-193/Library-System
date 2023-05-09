package com.springboot.first.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.first.model.Admin;
import com.springboot.first.model.Users;
import com.springboot.first.repository.AdminRepository;
import com.springboot.first.repository.UsersRepository;

@Service
public class CustomAdminDetailsService implements UserDetailsService {

	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Users users = usersRepository.findByUserName(username);
		if(users!=null) {
			return new UserPrincipal(users);
		}
		
		Admin admin=adminRepository.findByUserName(username);
		if(admin!=null) {
			return new AdminPrincipal(admin);
		}
		
		throw new UsernameNotFoundException("Username not found with :"+username);
	}
	
	

}
