package com.springboot.first.contoller;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.first.Security.JwtTokenProvider;
import com.springboot.first.model.Admin;
import com.springboot.first.model.Role;
import com.springboot.first.model.Users;
import com.springboot.first.repository.AdminRepository;
import com.springboot.first.repository.UsersRepository;
import com.springboot.first.service.AdminService;
import com.springboot.first.service.UsersService;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
//	@Autowired
	private UsersRepository usersRepository;
//	@Autowired
	private AdminRepository adminRepository;
	
	private UsersService userService;
	
	private AdminService adminService;
	
	  
	public AuthController(UsersRepository usersRepository, AdminRepository adminRepository, UsersService userService,
			AdminService adminService) {
		super();
		this.usersRepository = usersRepository;
		this.adminRepository = adminRepository;
		this.userService = userService;
		this.adminService = adminService;
	}

	@PostMapping("/login/admin")
	  public Map<Object, Object> loginAdmin(@RequestBody Admin admin) {
			Map<Object, Object> hashMap = new HashMap<Object,Object>();
			Admin list = adminRepository.findByUserName(admin.getUserName());
			if(list != null) {
				String token = jwtTokenProvider.generateAdminToken(list);
				hashMap.put("token", token);
			}else {
				hashMap.put("Error Message", "Invalid username or password.");
			}
		return  hashMap;
		}
	  
	  @PostMapping("/login/user")
	  
	  public Map<Object, Object> loginUsers(@RequestBody Users users) {
			Map<Object, Object> hashMap = new HashMap<Object,Object>();
			Users list = usersRepository.findByUserName(users.getUserName());
			if(list != null) {
				String token = jwtTokenProvider.generateUsersToken(list);
				hashMap.put("token", token);
			}else {
				hashMap.put("Error Message", "Invalid username or password.");
			}
		return  hashMap;
		}
	  
	  @PostMapping("/register/admin")
	    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {

	        admin.setRole(Role.ADMIN);

	        return new ResponseEntity<Admin>(adminService.saveAdmin(admin), HttpStatus.CREATED);
	    }
	  
	  @PostMapping("/register/users")
	  public ResponseEntity<?> saveUser(@RequestParam("userName") String userName, @RequestParam("email") String email,
				@RequestParam("contact") String contact, @RequestParam("joiningDate") String joiningDate,
				@RequestParam("profile") MultipartFile profile) throws IOException {
			try {
				 Users users=new Users();
				 users.setUserName(userName);
				 users.setEmail(email);
				 users.setContact(contact);
				 users.setJoiningDate(joiningDate);
				 users.setRole(Role.USER);
				 Path tempfile = Files.createTempFile("UserProfile", ".jpg");
					Files.write(tempfile, profile.getBytes());
					users.setProfile(tempfile.toString());
					
					userService.saveUsers(users);
					return new ResponseEntity<Users>(users, HttpStatus.CREATED);
			} catch (IllegalArgumentException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
						.body("{\"error\": \"" + "Email already exists" + "\"}");
			}
		}
	  
}
