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
    
    @Lob
	private String fees;
    
    private boolean deleted;

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



    public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}
	
	

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Trainer() {
    }
    public Trainer(@NotBlank @Size(max = 50) @Email String email,
                   String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, 
                   @NotNull String phoneNumber,Set<Role> roles,@NotBlank @Size(max = 20)  String firstName,@NotBlank String lastName,
                   String gender, String fees  ) {
    	

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
        this.gender = gender;
        this.fees=fees ; 
        //super.setValidated(false);

    }

    public Trainer(String email,
                   String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber,Set<Role> roles,@NotNull String firstName, @NotNull String lastName, @NotNull String gender,
                   Set<Days> disponibilityDays, Set<String>  specifications , String fees) {
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
        this.fees=fees ; 
    }



}