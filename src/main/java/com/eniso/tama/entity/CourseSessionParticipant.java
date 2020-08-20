package com.eniso.tama.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class CourseSessionParticipant {

	@EmbeddedId
	Long id;

	@ManyToOne
	@MapsId("student_id")
	@JoinColumn(name = "participant_id")
	Participant participant;

	@ManyToOne
	@MapsId("courseSession_id")
	@JoinColumn(name = "courseSession_id")
	CourseSession courseSession;

	boolean absent;

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

	public CourseSession getCourseSession() {
		return courseSession;
	}

	public void setCourseSession(CourseSession courseSession) {
		this.courseSession = courseSession;
	}

	public boolean isAbsent() {
		return absent;
	}

	public void setAbsent(boolean absent) {
		this.absent = absent;
	}
	
	
}
