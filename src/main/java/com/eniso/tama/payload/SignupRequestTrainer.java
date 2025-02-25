package com.eniso.tama.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.*;

import com.eniso.tama.entity.Days;


public class SignupRequestTrainer {
	@NotBlank
    @Size(max = 50)
    @Email
    private String email;


	@NotNull
	private String street;
	
	@NotNull
	private String city;
	
	@NotNull
	private String postalCode;
	
	@NotBlank
	@Size(min = 3, max = 20)
    private String phoneNumber;
    
	@NotBlank
	@Size(max = 20)
	private String firstName;
	
	@NotBlank
	@Size(max = 30)
	private String lastName;
	
	@NotBlank
	private String gender;

	private Set<String> specifications;

    private Set<Days> disponibilityDays;
    
    private Set<String> role;
    
   // @Size(min = 8, max = 40)
    private String password;
	
    
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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



	public Set<String> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Set<String> specifications) {
		this.specifications = specifications;
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

	public Set<Days> getDisponibilityDays() {
		return disponibilityDays;
	}

	public void setDisponibilityDays(Set<Days> disponibilityDays) {
		this.disponibilityDays = disponibilityDays;
	}

	
}

