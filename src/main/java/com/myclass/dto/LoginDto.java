package com.myclass.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class LoginDto {
	@NotEmpty(message = "Email cannot empty!")
	@Email(message = "Email is not valid!")
	private String email;
	
	@NotEmpty(message = "Password cannot empty!")
	@Length(min = 8,message = "Password must be at least 8 characters!")
	private String password;

	public LoginDto() {
		super();
	}

	public LoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
