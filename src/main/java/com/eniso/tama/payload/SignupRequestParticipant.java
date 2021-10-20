package com.eniso.tama.payload;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.ProgramInstance;

import java.time.LocalDate;

public class SignupRequestParticipant {
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

//	@NotBlank
//	@Size(min = 3, max = 20)
//	private String address;
	@NotNull
	private String street;

	@NotNull
	private String city;

	@NotNull
	private String postalCode;

	
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

	// @NotBlank
	private String field;

	private Set<String> role;

	@NotBlank
	@Size(min = 8, max = 40)
	private String password;

	private LocalDate birthday;

	private String currentPosition;

	private int experience;

	private String level;

	private String educationLevel;

	private Entreprise entreprise;

	private Set<ProgramInstance> programInstance;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		this.postalCode = postalCode;
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public int getExperience() {
		return experience;
	}

	public String getLevel() {
		return level;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}


	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Set<ProgramInstance> getProgramInstance() {
		return programInstance;
	}

	public void setProgramInstance(Set<ProgramInstance> programInstance) {
		this.programInstance = programInstance;
	}


}