package com.springboot.first.service;

import java.util.List;


import com.springboot.first.model.Admin;

public interface AdminService {
	Admin saveAdmin(Admin admin);
	List<Admin> getAdmins();
}
