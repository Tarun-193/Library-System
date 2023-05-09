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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "Email" }),
		@UniqueConstraint(columnNames = { "Contact" }), @UniqueConstraint(columnNames = { "Profile" }) })
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	private long userId;

	@NotNull
	@NotEmpty(message = "BookName cannot be empty")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Name must contain only alphabets")
	@Column(name = "UserName")
	private String userName;

	@Column(name = "Password")
	private String password;

	@NotNull
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Invalid email address")
	@Column(name = "Email")
	private String email;

	@NotNull
	@NotEmpty(message = "Contact cannot be empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Contact number must be a 10-digit number")
	@Column(name = "Contact")
	private String contact;

	@NotNull
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Joining date must be in 'yyyy-mm-dd' format.")
	@Column(name = "JoiningDate")
	private String joiningDate;

	@NotNull
	@NotEmpty
	@Column(name = "Profile")
	private String profile;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
