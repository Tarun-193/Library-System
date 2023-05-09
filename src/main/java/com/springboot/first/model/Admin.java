package com.springboot.first.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "Email" }),
		@UniqueConstraint(columnNames = { "Contact" }) })
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long id;

	@Pattern(regexp = "^[a-zA-Z]*$", message = "Name must contain only alphabets")
	@Column(name = "UserName")
	private String userName;

	@Column(name = "Password")
	private String password;

	@Email(message = "Invalid email address")
	@Column(name = "Email")
	private String email;

	@Pattern(regexp = "(^$|[0-9]{10})", message = "Contact number must be a 10-digit number")
	@Column(name = "Contact")
	private String contact;

	@Enumerated(EnumType.STRING)
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
