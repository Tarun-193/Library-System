package com.springboot.first.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	USER,
	ADMIN;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name();
	}
}
