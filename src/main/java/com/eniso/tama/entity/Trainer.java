package com.eniso.tama.entity;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="trainer")
public class Trainer extends User {
	


	@NotNull
	@Column(name = "firstName")
	private String firstName;
	
	@NotNull
	@Column(name = "lastName")
	private String lastName;
	
	@NotNull
	@Column(name = "gender")
	private String gender;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@NotNull
	@Column(name = "field")
	private String field;
}
