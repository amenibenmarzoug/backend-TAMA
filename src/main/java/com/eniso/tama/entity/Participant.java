package com.eniso.tama.entity;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="participant")
@PrimaryKeyJoinColumn(name = "user_id")

public class Participant extends User{
	

	@NotNull
	@Column(name = "firstNameP")
	private String firstNameP;

	@NotNull
	@Column(name = "lastNameP")
	private String lastNameP;
	
	
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

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public boolean isAbandon() {
		return abandon;
	}

	public void setAbandon(boolean abandon) {
		this.abandon = abandon;
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

	@NotNull
	@Column(name = "gender")
	private String gender;

	@NotNull
	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "currentPosition")
	private String currentPosition;

	@Column(name = "level")
	private String level;

	@Column(name = "educationLevel")
	private String educationLevel;

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	@NotNull
	@Column(name = "abandon")
	private boolean abandon;

	@ManyToOne
	private Entreprise entreprise;

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	@ManyToOne
	private Cursus cursus;

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "participant")
    Set<CourseSessionParticipant> courseSessionParticipant;
	
	public Set<CourseSessionParticipant> getCourseSessionParticipant() {
		return courseSessionParticipant;
	}

	public void setCourseSessionParticipant(Set<CourseSessionParticipant> courseSessionParticipant) {
		this.courseSessionParticipant = courseSessionParticipant;
	}

	
	
	public Participant() {
		
	}
	
	public Participant(@NotBlank @Size(max = 50) @Email String email,
			String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber,Set<Role> roles,@NotBlank @Size(max = 20)  String firstName,
			@NotBlank String lastName, @NotBlank String gender , Date birthday) {
		this.setId(super.getId());
		super.setEmail(email);
		super.setPassword(password);
		//super.setAddress(address);
		super.setStreet(street);
		super.setCity(city);
		super.setPostalCode(postalCode);
		super.setPhoneNumber(phoneNumber);
		super.setRoles(roles);
		this.firstNameP = firstName;
		this.lastNameP  = lastName;
		this.gender = gender;
		this.birthday= birthday;
		
	}
}
