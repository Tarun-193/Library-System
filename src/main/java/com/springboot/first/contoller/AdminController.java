package com.springboot.first.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.first.Security.JwtTokenProvider;
import com.springboot.first.exception.ResoureNotFoundException;
import com.springboot.first.model.Admin;
import com.springboot.first.repository.AdminRepository;
import com.springboot.first.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private AdminService adminService;

	private AdminRepository adminRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public AdminController(AdminService adminService, AdminRepository adminRepository,
			JwtTokenProvider jwtTokenProvider) {
		super();
		this.adminService = adminService;
		this.adminRepository = adminRepository;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@GetMapping
	public List<Admin> getAll() {
		return adminService.getAdmins();
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateAdmin(@PathVariable("id") long id, @Valid @RequestBody Admin admin) {
		Admin existingAdmin = adminRepository.findById(id)
				.orElseThrow(() -> new ResoureNotFoundException("Admin", "id", id));
		String emailString = admin.getEmail();

		if (emailString != null && !emailString.equals(existingAdmin.getEmail())) {
			if (adminRepository.existsByEmail(emailString)) {
				return ResponseEntity.badRequest().body(Map.of("error", "email already exists"));
			}
			existingAdmin.setEmail(emailString);
		}

		String contactString = admin.getContact();
		if (contactString != null && !contactString.equals(existingAdmin.getContact())) {
			if (adminRepository.existsByContact(contactString)) {
				return ResponseEntity.badRequest().body(Map.of("error", "Contact already exists"));
			}
			existingAdmin.setContact(contactString);
		}

		String usernameString = admin.getUserName();
		if (usernameString != null && !usernameString.equals(existingAdmin.getUserName())) {
			if (adminRepository.existsByUserName(usernameString)) {
				return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
			}
			existingAdmin.setUserName(usernameString);
		}
		return ResponseEntity.ok(adminRepository.save(existingAdmin));
	}

}
