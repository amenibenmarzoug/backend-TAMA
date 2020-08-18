package com.eniso.tama.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name="entreprise")
public class Entreprise extends User{
	

	@NotNull
	@Column(name = "entrepriseName")
	private String entrepriseName;
	

	
	@Column(name = "website")
	private String website;
	
	public String getEntrepriseName() {
		return entrepriseName;
	}

	public void setEntrepriseName(String entrepriseName) {
		this.entrepriseName = entrepriseName;
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
			String password, @NotBlank String address, @NotNull String phoneNumber,@NotBlank String enterpriseName, String website) {
		super.setEmail(email);
		super.setPassword(password);
		super.setAddress(address);
		super.setPhoneNumber(phoneNumber);
		this.entrepriseName = enterpriseName;
		this.website=website;
	}

	
	
	
}
