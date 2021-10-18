package com.eniso.tama.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Status;

public class ParticipantDto {
	  private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String firstNameP;
	
	private String lastNameP;
	
	private String gender;
	
	private LocalDate birthday;
	
	private String currentPosition;
	
	private int experience;
	
	private int age;
	private String level;
	private String educationLevel;
	private boolean abandon;
	private Entreprise entreprise;
	private Status status;
	private Boolean validated = false;

	private String email;

	private String street;

	private String city;

	private String postalCode;

	private String phoneNumber;
	 private String password;

	private Instant createdDate = Instant.now();

	private Instant lastModifiedDate = Instant.now();

	private Set<Role> roles = new HashSet<>();

    private Set<ProgramInstance> programInstance;

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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public boolean isAbandon() {
		return abandon;
	}

	public void setAbandon(boolean abandon) {
		this.abandon = abandon;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Boolean getValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

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

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

	
	

	public Set<ProgramInstance> getProgramInstance() {
		return programInstance;
	}

	public void setProgramInstance(Set<ProgramInstance> programInstance) {
		this.programInstance = programInstance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	

	public ParticipantDto(Long id, String firstNameP, String lastNameP, String gender, LocalDate birthday,
			String currentPosition, int experience, int age, String level, String educationLevel, boolean abandon,
			Entreprise entreprise, Status status, Boolean validated, String email, String street, String city,
			String postalCode, String phoneNumber, String password, Instant createdDate, Instant lastModifiedDate,
			Set<Role> roles, Set<ProgramInstance> classes) {
		super();
		this.id = id;
		this.firstNameP = firstNameP;
		this.lastNameP = lastNameP;
		this.gender = gender;
		this.birthday = birthday;
		this.currentPosition = currentPosition;
		this.experience = experience;
		this.age = age;
		this.level = level;
		this.educationLevel = educationLevel;
		this.abandon = abandon;
		this.entreprise = entreprise;
		this.status = status;
		this.validated = validated;
		this.email = email;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.roles = roles;
		this.programInstance = classes;
	}

	public ParticipantDto() {
		super();
	}



}

