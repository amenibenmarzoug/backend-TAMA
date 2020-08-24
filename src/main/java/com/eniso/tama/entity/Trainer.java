package com.eniso.tama.entity;




import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	//@NotNull
	@Column(name = "field")
	private String field;
	
	@OneToMany(mappedBy = "trainer")
    Set<TrainerDisponibility> trainerDisponibility;
	
	
	
	public Set<TrainerDisponibility> getTrainerDisponibility() {
		return trainerDisponibility;
	}

	public void setTrainerDisponibility(Set<TrainerDisponibility> trainerDisponibility) {
		this.trainerDisponibility = trainerDisponibility;
	}

	public Trainer() {
	}
	public Trainer(@NotBlank @Size(max = 50) @Email String email,
			String password, @NotBlank String address, @NotNull String phoneNumber,@NotBlank @Size(max = 20)  String firstName,
			@NotBlank String lastName,  String gender ) {
		this.setId(super.getId());
		super.setEmail(email);
		super.setPassword(password);
		super.setAddress(address);
		super.setPhoneNumber(phoneNumber);
		this.firstName = firstName;
		this.lastName  = lastName;
		this.gender = gender;
		
	}
	
}
