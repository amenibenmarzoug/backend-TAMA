package com.eniso.tama.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class ProgramInstance {

	@Id
	@Column(name = "programInstance_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column
	private String programInstName;

	@Column
	private Date beginDate;

	@Column
	private Date endDate;

	@NotNull
	@Column(name = "ProgramInstDays")
	private int nbDaysProgInst;

	@NotNull
	@Column
	private String location;

	@Column
	private boolean validated = false;

	@Column
	private int nbMinParticipants;

	@Lob
	private String place;

	@ManyToOne
	private Program program;

	@OneToMany
	private List<CompanyRegistration> Companyregistrations;

	public ProgramInstance() {
	}

	public ProgramInstance(Long id, @NotNull String programInstName, @NotNull int nbDaysProgInst,
			@NotNull String location, Program program) {
		super();
		this.id = id;
		this.programInstName = programInstName;
		this.nbDaysProgInst = nbDaysProgInst;
		this.location = location;
		this.program = program;
	}

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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public List<CompanyRegistration> getCompanyregistrations() {
		return Companyregistrations;
	}

	public void setCompanyregistrations(List<CompanyRegistration> companyregistrations) {
		Companyregistrations = companyregistrations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Companyregistrations == null) ? 0 : Companyregistrations.hashCode());
		result = prime * result + ((beginDate == null) ? 0 : beginDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + nbDaysProgInst;
		result = prime * result + nbMinParticipants;
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((program == null) ? 0 : program.hashCode());
		result = prime * result + ((programInstName == null) ? 0 : programInstName.hashCode());
		result = prime * result + (validated ? 1231 : 1237);
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
		ProgramInstance other = (ProgramInstance) obj;
		if (Companyregistrations == null) {
			if (other.Companyregistrations != null)
				return false;
		} else if (!Companyregistrations.equals(other.Companyregistrations))
			return false;
		if (beginDate == null) {
			if (other.beginDate != null)
				return false;
		} else if (!beginDate.equals(other.beginDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (nbDaysProgInst != other.nbDaysProgInst)
			return false;
		if (nbMinParticipants != other.nbMinParticipants)
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (program == null) {
			if (other.program != null)
				return false;
		} else if (!program.equals(other.program))
			return false;
		if (programInstName == null) {
			if (other.programInstName != null)
				return false;
		} else if (!programInstName.equals(other.programInstName))
			return false;
		if (validated != other.validated)
			return false;
		return true;
	}
	
	

	

}