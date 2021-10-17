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
@PrimaryKeyJoinColumn(name = "user_id")
public class Trainer extends User {


    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private String lastName;

    @NotNull
    @Column
    private String gender;

    @ElementCollection
    @CollectionTable(name = "trainer_disponibility_days", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "day")
    private Set<Days> disponibilityDays = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "trainer_specifications", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "specifications")
    private Set<String> specifications = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "trainer")
    Set<TrainerDisponibility> trainerDisponibility;
    
    public Trainer() {
    }
    
    public Trainer(@NotBlank @Size(max = 50) @Email String email,
                   String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber,Set<Role> roles,@NotBlank @Size(max = 20)  String firstName,@NotBlank String lastName, String gender ) {

        this.setId(super.getId());
        super.setEmail(email);
        super.setPassword(password);
        super.setStreet(street);
        super.setCity(city);
        super.setPostalCode(postalCode);
        super.setPhoneNumber(phoneNumber);
        super.setRoles(roles);
        this.firstName = firstName;
        this.lastName  = lastName;
        this.gender = gender;

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


    public Set<Days> getDisponibilityDays() {
        return disponibilityDays;
    }

    public void setDisponibilityDays(Set<Days> disponibilityDays) {
        this.disponibilityDays = disponibilityDays;
    }



    public Set<TrainerDisponibility> getTrainerDisponibility() {
        return trainerDisponibility;
    }

    public void setTrainerDisponibility(Set<TrainerDisponibility> trainerDisponibility) {
        this.trainerDisponibility = trainerDisponibility;
    }



    public Set<String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Set<String> specifications) {
        this.specifications = specifications;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disponibilityDays == null) ? 0 : disponibilityDays.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((specifications == null) ? 0 : specifications.hashCode());
		result = prime * result + ((trainerDisponibility == null) ? 0 : trainerDisponibility.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainer other = (Trainer) obj;
		if (disponibilityDays == null) {
			if (other.disponibilityDays != null)
				return false;
		} else if (!disponibilityDays.equals(other.disponibilityDays))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (specifications == null) {
			if (other.specifications != null)
				return false;
		} else if (!specifications.equals(other.specifications))
			return false;
		if (trainerDisponibility == null) {
			if (other.trainerDisponibility != null)
				return false;
		} else if (!trainerDisponibility.equals(other.trainerDisponibility))
			return false;
		return true;
	}



}