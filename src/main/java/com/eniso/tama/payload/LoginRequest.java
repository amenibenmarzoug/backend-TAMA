package com.eniso.tama.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	public LoginRequest() {
		super();
	}

	public LoginRequest(@NotBlank String email, @NotBlank String password , boolean validated) {
		super();
		this.email = email;
		this.password = password;
		this.validated= validated ;
	}

	//@NotBlank
	private String email;

	//@NotBlank
	private String password;
	
	private boolean validated;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
