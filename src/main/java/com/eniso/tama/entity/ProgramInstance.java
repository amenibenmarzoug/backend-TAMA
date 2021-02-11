package com.eniso.tama.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "programInstance")

public class ProgramInstance {

	@Id
	@Column(name = "programInstance_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name = "programInstName")
	private String programInstName;
	
	@Column(name = "dateDebut")
	private Date dateDebut;
	
	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getProgramInstName() {
		return programInstName;
	}

	public void setProgramInstName(String programInstName) {
		this.programInstName = programInstName;
	}

	public int getNbDaysProgInst() {
		return nbDaysProgInst;
	}

	

	public void setNbDaysProgInst(int nbDaysProgInst) {
		this.nbDaysProgInst = nbDaysProgInst;
	}
	@NotNull
	@Column(name="ProgramInstDays")
	private int nbDaysProgInst;
	
	@NotNull
	@Column(name = "location")
	private String location;

	public Long getId() {
		return id;
	}  
	@ManyToOne
	private Program program;
	
	
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public ProgramInstance() {}
	public ProgramInstance(Long id, @NotNull String programInstName, @NotNull int nbDaysProgInst,
			@NotNull String location, Program program) {
		super();
		this.id = id;
		this.programInstName = programInstName;
		this.nbDaysProgInst = nbDaysProgInst;
		this.location = location;
		this.program = program;
	}
}