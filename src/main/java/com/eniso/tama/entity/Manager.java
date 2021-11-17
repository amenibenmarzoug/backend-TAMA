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
    
	private boolean deleted;


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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

    
}
