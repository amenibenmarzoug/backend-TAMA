package com.eniso.tama.dto;

import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Session;

public class AttendanceDTO {



	private Participant participant;

	private Session session;

	private String attendanceState;

	public AttendanceDTO() {
	}

	public AttendanceDTO(Participant participant, Session session, String attendanceState) {
		super();
		this.participant = participant;
		this.session = session;
		this.attendanceState = attendanceState;
	}

	
	public Participant getParticipant() {
		return participant;
	}

	public Session getSession() {
		return session;
	}

	public String getAttendanceState() {
		return attendanceState;
	}



	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void setAttendanceState(String attendanceState) {
		this.attendanceState = attendanceState;
	}

}
