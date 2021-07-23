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
public class ProgramInstance {

	@Id
	@Column(name = "programInstance_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column
	private String programInstName;
	
	@Column
	private Date beginDate;
	
	
	@Column
	private Date endDate;
	
	@NotNull
	@Column(name="ProgramInstDays")
	private int nbDaysProgInst;
	
	@NotNull
	@Column
	private String location;

	@Column
	private boolean validated=false;
	
	@Column
	private int nbMinParticipants;
	
	@ManyToOne
	private Program program;
	
	


	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
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
	
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}
	public Long getId() {
		return id;
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

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public int getNbMinParticipants() {
		return nbMinParticipants;
	}

	public void setNbMinParticipants(int nbMinParticipants) {
		this.nbMinParticipants = nbMinParticipants;
	}
	
	



	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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