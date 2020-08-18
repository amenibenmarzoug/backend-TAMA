package com.eniso.tama.entity;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="institution")
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

	public Institution(@NotBlank @Size(max = 50) @Email String email,
			String password, @NotBlank String address, @NotNull String phoneNumber,@NotBlank String institutionName) {
		super.setEmail(email);
		super.setPassword(password);
		super.setAddress(address);
		super.setPhoneNumber(phoneNumber);
		this.institutionName = institutionName;
	}

}
