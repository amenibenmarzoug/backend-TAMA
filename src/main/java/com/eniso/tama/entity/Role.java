package com.eniso.tama.entity;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="role")
public class Role {
	
	 @Id
	 @Column(name = "role_id", updatable = false, nullable = false)
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer id;
	 
	 
	 @NotNull
	 @Enumerated(EnumType.STRING)
	 private Roles role;
	 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
}