package com.eniso.tama.entity;

import java.util.Date;

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
public class Attendance {
	@Id
	@Column(name = "attendance_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	 @Column(name = "created_date")
	 private Date createdDate;
	 
	 @Column(name = "last_modified_date")
	 private Date lastModifiedDate;

	@ManyToOne
	@JoinColumn(name = "participant_id")
	Participant participant;

	@ManyToOne
	@JoinColumn(name = "session_id")
	Session session;

	private AttendanceStates attendanceState;

	private boolean deleted;

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

	public AttendanceStates getAttendanceState() {
		return attendanceState;
	}

	public void setAttendanceState(AttendanceStates attendanceState) {
		this.attendanceState = attendanceState;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
