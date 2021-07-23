package com.eniso.tama.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Session {
	@Id
	@Column(name = "session_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column
	private String sessionName;

	@Column(name = "session_begin_date")
	private Date sessionBeginDate;

	@Column(name = "session_end_date")
	private Date sessionEndDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	private ThemeDetailInstance themeDetailInstance;

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public Date getSessionBeginDate() {
		return sessionBeginDate;
	}

	public void setSessionBeginDate(Date sessionBeginDate) {
		this.sessionBeginDate = sessionBeginDate;
	}

	public Date getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(Date sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}



	public ThemeDetailInstance getThemeDetailInstance() {
		return themeDetailInstance;
	}

	public void setThemeDetailInstance(ThemeDetailInstance themeDetailInstance) {
		this.themeDetailInstance = themeDetailInstance;
	}


	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	@ManyToOne
	private ClassRoom classRoom;

	@ManyToOne
	private Trainer trainer;

//	@ManyToOne
//	private Group group;

	@JsonIgnore
	@OneToMany(mappedBy = "session")
	Set<SessionParticipant> sessionParticipant;

	public Set<SessionParticipant> getSessionParticipant() {
		return sessionParticipant;
	}

	public void setSessionParticipant(Set<SessionParticipant> sessionParticipant) {
		this.sessionParticipant = sessionParticipant;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	

	@JsonIgnore
	@OneToOne(mappedBy = "session")
	private Event event;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	

}
