package com.eniso.tama.payload;

import java.util.Set;

import javax.validation.constraints.*;


public class SignupRequestTrainer {
	@NotBlank
    @Size(max = 50)
    @Email
    private String email;
	
	@NotBlank
    @Size(min = 3, max = 20)
    private String address;
    
	@NotBlank
	@Size(min = 3, max = 20)
    private String phoneNumber;
    
 
 
    private Set<String> role;
    
    @NotBlank
    @Size(min = 8, max = 40)
    private String password;
  
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank
	@Size(max = 20)
	private String firstName;
	
	@NotBlank
	@Size(max = 30)
	private String lastName;
	
	//@NotBlank
	private String field;
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@NotBlank
	private String gender;

}
