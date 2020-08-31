package com.eniso.tama.payload;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.eniso.tama.entity.Entreprise;

public class SignupRequestParticipant {
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	private Entreprise entreprise;

public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	//	@NotBlank
//	@Size(min = 3, max = 20)
//	private String address;
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
	private String firstNameP;

	@NotBlank
	@Size(max = 30)
	private String lastNameP;

	@NotBlank
	private String gender;

	// @NotBlank
	private String field;

	private Set<String> role;

	@NotBlank
	@Size(min = 8, max = 40)
	private String password;

	
	private Date birthday;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

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
		this.postalCode = postalCode;}


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

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFirstNameP() {
		return firstNameP;
	}

	public void setFirstNameP(String firstNameP) {
		this.firstNameP = firstNameP;
	}

	public String getLastNameP() {
		return lastNameP;
	}

	public void setLastNameP(String lastNameP) {
		this.lastNameP = lastNameP;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
