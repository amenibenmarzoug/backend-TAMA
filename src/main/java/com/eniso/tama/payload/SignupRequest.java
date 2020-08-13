package com.eniso.tama.payload;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {
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
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
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
}
