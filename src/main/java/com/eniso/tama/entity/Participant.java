package com.eniso.tama.entity;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="participant")
public class Participant extends User{
	

	//@NotNull
	@Column(name = "firstNameP")
	private String firstNameP;
	


	//@NotNull
	@Column(name = "lastNameP")
	private String lastNameP;
	
	


	//@NotNull
	@Column(name = "gender")
	private String gender;
	
	//@NotNull
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "currentPosition")
	private String currentPosition;
	
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "educationLevel")
	private String educationLevel;
	
	//@NotNull
		@Column(name = "abandon")
	private boolean abandon;
		

		  @ManyToOne
		  private Entreprise entreprise;
	
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
}
