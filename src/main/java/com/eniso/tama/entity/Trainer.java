package com.eniso.tama.entity;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="trainer")
@PrimaryKeyJoinColumn(name = "user_id")
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


	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
    @ElementCollection
    @CollectionTable(name = "trainer_disponibility_days", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "day")
    private Set<Days> disponibilityDays = new HashSet<>();

 
	
	public Set<Days> getDisponibilityDays() {
		return disponibilityDays;
	}

	public void setDisponibilityDays(Set<Days> disponibilityDays) {
		this.disponibilityDays = disponibilityDays;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "trainer")
    Set<TrainerDisponibility> trainerDisponibility;
	
	
	
	public Set<TrainerDisponibility> getTrainerDisponibility() {
		return trainerDisponibility;
	}

	public void setTrainerDisponibility(Set<TrainerDisponibility> trainerDisponibility) {
		this.trainerDisponibility = trainerDisponibility;
	}

	@ElementCollection
    @CollectionTable(name = "trainer_specifications", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "specifications")
    private Set<String> specifications = new HashSet<>();
	
	
	public Set<String> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Set<String> specifications) {
		this.specifications = specifications;
	}

	@NotNull
	@Column(name = "specification")
	private String specification;
	
	public Trainer() {
	}
	public Trainer(@NotBlank @Size(max = 50) @Email String email,
			String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber,Set<Role> roles,@NotBlank @Size(max = 20)  String firstName,

			@NotBlank String lastName,Set<String>  specifications, String gender ) {

		this.setId(super.getId());
		super.setEmail(email);
		super.setPassword(password);
		//super.setAddress(address);
		super.setStreet(street);
		super.setCity(city);
		super.setPostalCode(postalCode);
		super.setPhoneNumber(phoneNumber);
		super.setRoles(roles);
		this.firstName = firstName;
		this.lastName  = lastName;
		this.specifications = specifications;
		this.gender = gender;
		//super.setValidated(false);
		
	}

	public Trainer(String email,
			String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber,Set<Role> roles,@NotNull String firstName, @NotNull String lastName, @NotNull String gender,
			Set<Days> disponibilityDays, Set<String>  specifications) {
		this.setId(super.getId());
		super.setEmail(email);
		super.setPassword(password);
		super.setStreet(street);
		super.setCity(city);
		super.setPostalCode(postalCode);
		super.setPhoneNumber(phoneNumber);
		super.setRoles(roles);
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.disponibilityDays = disponibilityDays;
		this.specifications=specifications;
	}
	
	
	
}
