package com.eniso.tama.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name="entreprise")
public class Entreprise extends User{
	

	@NotNull
	@Column(name = "entrepriseName")
	private String entrepriseName;
	
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

	@URL
	@Column(name = "website")
	private String website;
	
	

	
	
	
}
