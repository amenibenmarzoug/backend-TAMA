package com.eniso.tama.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class SessionParticipant {

	@Id
    @Column(name = "sessionP_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "participant_id")
	Participant participant;

	@ManyToOne
	@JoinColumn(name = "session_id")
	Session session;

	private PresenceStates presenceState;

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

	public PresenceStates getPresenceState() {
		return presenceState;
	}

	public void setPresenceState(PresenceStates presenceState) {
		this.presenceState = presenceState;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	



	
	
	
	//public SessionParticipant() {}
	
}
