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
public class TrainerDisponibility {

	@Id
    @Column(name = "trainerDispo_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long idTrainerDisponibility;

	@ManyToOne
	@JoinColumn(name = "trainer_id")
	Trainer trainer;

	/*@ManyToOne
	@JoinColumn(name = "courseSession_id")
	Session courseSession;*/

	@Column
	boolean disponibility;

	public Long getIdTrainerDisponibility() {
		return idTrainerDisponibility;
	}

	public void setIdTrainerDisponibility(Long idTrainerDisponibility) {
		this.idTrainerDisponibility = idTrainerDisponibility;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	/*public Session getCourseSession() {
		return courseSession;
	}

	public void setCourseSession(Session courseSession) {
		this.courseSession = courseSession;
	}*/

	public boolean isDisponibility() {
		return disponibility;
	}

	public void setDisponibility(boolean disponibility) {
		this.disponibility = disponibility;
	}
	
	public TrainerDisponibility() {}
}
