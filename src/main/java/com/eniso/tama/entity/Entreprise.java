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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enterpriseName == null) ? 0 : enterpriseName.hashCode());
		result = prime * result + ((managerFirstName == null) ? 0 : managerFirstName.hashCode());
		result = prime * result + ((managerLastName == null) ? 0 : managerLastName.hashCode());
		result = prime * result + ((managerPosition == null) ? 0 : managerPosition.hashCode());
		result = prime * result + nbMinParticipants;
		result = prime * result + (provider ? 1231 : 1237);
		result = prime * result + ((registrations == null) ? 0 : registrations.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		Entreprise other = (Entreprise) obj;
		if (enterpriseName == null) {
			if (other.enterpriseName != null)
				return false;
		} else if (!enterpriseName.equals(other.enterpriseName))
			return false;
		if (managerFirstName == null) {
			if (other.managerFirstName != null)
				return false;
		} else if (!managerFirstName.equals(other.managerFirstName))
			return false;
		if (managerLastName == null) {
			if (other.managerLastName != null)
				return false;
		} else if (!managerLastName.equals(other.managerLastName))
			return false;
		if (managerPosition == null) {
			if (other.managerPosition != null)
				return false;
		} else if (!managerPosition.equals(other.managerPosition))
			return false;
		if (nbMinParticipants != other.nbMinParticipants)
			return false;
		if (provider != other.provider)
			return false;
		if (registrations == null) {
			if (other.registrations != null)
				return false;
		} else if (!registrations.equals(other.registrations))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}
	
	
	

}
