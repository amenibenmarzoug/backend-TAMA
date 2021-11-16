package com.eniso.tama.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CompanyRegistration  implements Serializable {
	@Id
	@Column(name = "registration_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JsonIgnore 

	@JoinColumn(name = "entreprise_id")
	Entreprise entreprise;
	@ManyToOne
	@JoinColumn(name = "programinstance_id")
	ProgramInstance programinstance;
	
	private LocalDate registrationDate;
	
	private boolean deleted;

//	@OneToMany(fetch = FetchType.LAZY )
//	private List<ParticipantRegistration> participantRegistrations;
//	
//
//	public List<ParticipantRegistration> getParticipantRegistrations() {
//		return participantRegistrations;
//	}
//
//	public void setParticipantRegistrations(List<ParticipantRegistration> participantRegistrations) {
//		this.participantRegistrations = participantRegistrations;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public CompanyRegistration(Long id, Entreprise entreprise, ProgramInstance programinstance,
			LocalDate registrationDate, List<ParticipantRegistration> participantRegistrations) {
		super();
		this.id = id;
		this.entreprise = entreprise;
		this.programinstance = programinstance;
		this.registrationDate = registrationDate;
		//this.participantRegistrations = participantRegistrations;
	}

	public CompanyRegistration() {
		super();
	}

	@Override
	public String toString() {
		return "CompanyRegistration [id=" + id + ", entreprise=" + entreprise + ", programinstance=" + programinstance
				+ ", registrationDate=" + registrationDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entreprise == null) ? 0 : entreprise.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((programinstance == null) ? 0 : programinstance.hashCode());
		result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
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
		CompanyRegistration other = (CompanyRegistration) obj;
		if (entreprise == null) {
			if (other.entreprise != null)
				return false;
		} else if (!entreprise.equals(other.entreprise))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (programinstance == null) {
			if (other.programinstance != null)
				return false;
		} else if (!programinstance.equals(other.programinstance))
			return false;
		if (registrationDate == null) {
			if (other.registrationDate != null)
				return false;
		} else if (!registrationDate.equals(other.registrationDate))
			return false;
		return true;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public ProgramInstance getPrograminstance() {
		return programinstance;
	}

	public void setPrograminstance(ProgramInstance programinstance) {
		this.programinstance = programinstance;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate now) {
		this.registrationDate = now;
	}
	

}
