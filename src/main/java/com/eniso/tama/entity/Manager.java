package com.eniso.tama.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Manager extends User {

    @NotNull
    @Column
    private String firstNameM;

    @NotNull
    @Column
    private String lastNameM;
    

    public String getFirstNameM() {
        return firstNameM;
    }

    public void setFirstNameM(String firstNameM) {
        this.firstNameM = firstNameM;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstNameM == null) ? 0 : firstNameM.hashCode());
		result = prime * result + ((lastNameM == null) ? 0 : lastNameM.hashCode());
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
		Manager other = (Manager) obj;
		if (firstNameM == null) {
			if (other.firstNameM != null)
				return false;
		} else if (!firstNameM.equals(other.firstNameM))
			return false;
		if (lastNameM == null) {
			if (other.lastNameM != null)
				return false;
		} else if (!lastNameM.equals(other.lastNameM))
			return false;
		return true;
	}
    
    

}
