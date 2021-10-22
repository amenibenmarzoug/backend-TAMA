package com.eniso.tama.entity;

import java.util.*;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Participant extends User {

	@NotNull
	@Column
	private String firstNameP;


    
	@NotNull
	@Column
	private String lastNameP;


	@NotNull
	@Column
	private String gender;

	//@NotNull
	@Column
	private LocalDate birthday;

	@Column
	private String currentPosition;

	@NotNull
	@Column
	private int experience;

	@Formula(value = "YEAR(CURDATE()) - YEAR(BIRTHDAY)")
	private int age;

	@Column
	private String level;

	@Column
	private String educationLevel;

	@NotNull
	@Column
	private boolean abandon;

	@ManyToOne
	// @JsonIgnore
	private Entreprise entreprise;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participant")
//    private List<ParticipantRegistration> participantRegistrations; 

	@Enumerated(EnumType.STRING)
	private Status status;

	

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}


	public String getEducationLevel() {
		return educationLevel;
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



	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Participant() {

	}

	public Participant(@NotBlank @Size(max = 50) @Email String email, String password, @NotBlank String street,
			@NotBlank String city, @NotBlank String postalCode,  String phoneNumber, Set<Role> roles,
			@NotBlank @Size(max = 20) String firstName, @NotBlank String lastName, @NotBlank String gender,
			LocalDate birthday) {
		// this.setId(super.getId());
		super.setEmail(email);
		super.setPassword(password);
		// super.setAddress(address);
		super.setStreet(street);
		super.setCity(city);
		super.setPostalCode(postalCode);
		super.setPhoneNumber(phoneNumber);
		super.setRoles(roles);
		this.firstNameP = firstName;
		this.lastNameP = lastName;
		this.gender = gender;
		this.birthday = birthday;

	}

	public Participant(@NotNull String firstNameP, @NotNull String lastNameP, @NotNull String gender,
			@NotNull LocalDate birthday, String currentPosition, @NotNull int experience, int age, String level,
			String educationLevel, @NotNull boolean abandon, Entreprise entreprise,
			List<ParticipantRegistration> participantRegistrations, Status status) {
		super();
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
		// this.participantRegistrations = participantRegistrations;
		this.status = status;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;

	}

//	public List<ParticipantRegistration> getParticipantRegistrations() {
//		return participantRegistrations;
//	}
//
//	public void setParticipantRegistrations(List<ParticipantRegistration> participantRegistrations) {
//		this.participantRegistrations = participantRegistrations;
//	}

	@Override

	public String toString() {
		return "Participant [ firstNameP=" + firstNameP + ", lastNameP=" + lastNameP + ", gender=" + gender
				+ ", birthday=" + birthday + ", currentPosition=" + currentPosition + ", level=" + level
				+ ", educationLevel=" + educationLevel + ", abandon=" + abandon + ", entreprise=" + entreprise
				+ ", courseSessionParticipant=" + "]";
	}

}