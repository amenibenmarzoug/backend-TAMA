package com.eniso.tama.payload;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignupRequestInstitution {
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
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	private Set<String> role;
    
    @NotBlank
    @Size(min = 8, max = 40)
    private String password;
	@NotNull
	private String institutionName;

}
