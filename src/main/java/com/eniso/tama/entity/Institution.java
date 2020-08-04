package com.eniso.tama.entity;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="institution")
public class Institution extends User{
	
	@NotNull
	@Column(name = "institutionName")
	private String institutionName;

}
