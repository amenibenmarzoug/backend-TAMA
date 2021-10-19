package com.eniso.tama.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ParticipantRegistration {

	@Id
	@Column( updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "participant_id")
	Participant participant;

	@ManyToOne
	@JoinColumn(name = "programinstance_id")
	ProgramInstance programinstance;
	
	@ManyToOne
	@JoinColumn(name = "companyRegistration_id")
	CompanyRegistration companyRegistration;
	
	private LocalDate registrationDate;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
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

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public ParticipantRegistration() {
		super();
	}
	
	

	public CompanyRegistration getCompanyRegistration() {
		return companyRegistration;
	}

	public void setCompanyRegistration(CompanyRegistration companyRegistration) {
		this.companyRegistration = companyRegistration;
	}
	
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ParticipantRegistration(Long id, Participant participant, ProgramInstance programinstance,
			LocalDate registrationDate) {
		super();
		this.id = id;
		this.participant = participant;
		this.programinstance = programinstance;
		this.registrationDate = registrationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
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
		ParticipantRegistration other = (ParticipantRegistration) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (participant == null) {
			if (other.participant != null)
				return false;
		} else if (!participant.equals(other.participant))
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

	@Override
	public String toString() {
		return "ParticipantRegistration [id=" + id + ", participant=" + participant + ", programinstance="
				+ programinstance + ", registrationDate=" + registrationDate + "]";
	}

	
	
}
