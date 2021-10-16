package com.eniso.tama.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Entreprise extends User {


    //@NotNull
    @Column
    private String enterpriseName;

    @Column
    private String managerFirstName;

    @Column
    private String managerLastName;


    @Column
    private String managerPosition;

    @Column
    private String website;

    @Column
    private int nbMinParticipants;
    
    @Column
    private boolean provider=false;

   // @ManyToOne
    //@JsonIgnore
    //private ProgramInstance programInstance;
    @OneToMany
    private List<CompanyRegistration> registrations; 

    

	public Entreprise() {
    }

    public Entreprise(String enterpriseName, String website, String managerFirstName, String managerLastName) {
        this.enterpriseName = enterpriseName;
        this.website = website;
        this.managerFirstName = managerFirstName;
        this.managerLastName = managerLastName;
    }

    public Entreprise(@NotBlank @Size(max = 50) @Email String email,
                      String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber, Set<Role> roles,
                      String enterpriseName, String website, String managerFirstName, String managerLastName, String managerPosition, int nbParticip, boolean provider) {
        super.setEmail(email);
        super.setPassword(password);
        //super.setAddress(address);
        super.setStreet(street);
        super.setCity(city);
        super.setPostalCode(postalCode);
        super.setPhoneNumber(phoneNumber);
        this.enterpriseName = enterpriseName;
        super.setRoles(roles);
        this.website = website;
        this.managerFirstName = managerFirstName;
        this.managerLastName = managerLastName;
        this.managerPosition = managerPosition;
        this.nbMinParticipants = nbParticip;
        this.provider=provider;
    }

 

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getManagerPosition() {
        return managerPosition;
    }

    public void setManagerPosition(String managerPosition) {
        this.managerPosition = managerPosition;
    }

    public int getNbMinParticipants() {
        return nbMinParticipants;
    }

    public void setNbMinParticipants(int nbMinParticipants) {
        this.nbMinParticipants = nbMinParticipants;
    }

	public boolean isProvider() {
		return provider;
	}

	public void setProvider(boolean provider) {
		this.provider = provider;
	}

	public List<CompanyRegistration> getRegistration() {
		return registrations;
	}

	public void setRegistration(List<CompanyRegistration> registrations) {
		this.registrations = registrations;
	}
	

}
