package com.eniso.tama.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="entreprise")
@PrimaryKeyJoinColumn(name = "user_id")
public class Entreprise extends User{
	
	//this variable is for validating the accounts
		//@NotNull
//		@Column(name = "validated"  )
//		private boolean validated;
//
//
//		
//
//		public boolean isValidated() {
//			return validated;
//		}
//
//		public void setValidated(boolean validated) {
//			 this.validated=validated;
//		}
	
	@JsonIgnore
	@OneToMany(mappedBy = "entreprise")
    Set<EntrepriseDisponibility> entrepriseDisponibility;

	@ManyToOne
	private Cursus cursus;

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	} 
	//@NotNull
	@Column(name = "enterpriseName")
	private String enterpriseName;
	

	
	@Column(name = "website")
	private String website;
	
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

	public Entreprise() {
	}

	public Entreprise(@NotBlank @Size(max = 50) @Email String email,
			String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber,Set<Role> roles,String enterpriseName, String website) {
		super.setEmail(email);
		super.setPassword(password);
		//super.setAddress(address);
		super.setStreet(street);
		super.setCity(city);
		super.setPostalCode(postalCode);
		super.setPhoneNumber(phoneNumber);
		this.enterpriseName = enterpriseName;
		super.setRoles(roles);
		this.website=website;
	}
	


	
	
	
}
