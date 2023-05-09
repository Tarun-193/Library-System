package com.springboot.first.service.implementation;

import java.util.List;


import org.springframework.stereotype.Service;
import com.springboot.first.model.Admin;
import com.springboot.first.repository.AdminRepository;
import com.springboot.first.service.AdminService;

@Service
public class AdminServiceImplementation implements AdminService {
	private AdminRepository adminRepository;
	

	public AdminServiceImplementation(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public List<Admin> getAdmins() {
		return adminRepository.findAll();
	}

}
