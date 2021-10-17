package com.eniso.tama.entity;


import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Institution extends User {

    @NotNull
    @Column
    private String institutionName;


    public Institution() {
    }


    public Institution(@NotBlank @Size(max = 50) @Email String email,
                       String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber, Set<Role> roles, @NotBlank String institutionName) {

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

    
    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((institutionName == null) ? 0 : institutionName.hashCode());
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
		Institution other = (Institution) obj;
		if (institutionName == null) {
			if (other.institutionName != null)
				return false;
		} else if (!institutionName.equals(other.institutionName))
			return false;
		return true;
	}
    
    

}
