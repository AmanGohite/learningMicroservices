package com.learning.userservice.model;

import javax.validation.constraints.NotBlank;

public class CustomerLogin {
	
	@NotBlank(message="email cannot be blank")
	private String email;
	@NotBlank(message="password cannot be blank")
	private String password;
	
	public CustomerLogin() {}
	
	public CustomerLogin(String email, String password) {
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
