package com.eniso.tama.entity;


import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="institution")
@PrimaryKeyJoinColumn(name = "user_id")

public class Institution extends User{
	
	@NotNull
	@Column(name = "institutionName")
	private String institutionName;
	
	
	public String getInstitutionName() {
		return institutionName;
	}


	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public Institution() {
	}

	public Institution(@NotBlank @Size(max = 50) @Email String email,String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber,Set<Role> roles,@NotBlank String institutionName) {
		super.setEmail(email);
		super.setPassword(password);
		//super.setAddress(address);
		super.setStreet(street);
		super.setCity(city);
		super.setPostalCode(postalCode);
		super.setPhoneNumber(phoneNumber);
		super.setRoles(roles);
		this.institutionName = institutionName;
	}

}
