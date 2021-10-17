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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idTrainerDisponibility;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    Trainer trainer;

    @Column
    boolean disponibility;

    public TrainerDisponibility() {
    }

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

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (disponibility ? 1231 : 1237);
		result = prime * result + ((idTrainerDisponibility == null) ? 0 : idTrainerDisponibility.hashCode());
		result = prime * result + ((trainer == null) ? 0 : trainer.hashCode());
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
		TrainerDisponibility other = (TrainerDisponibility) obj;
		if (disponibility != other.disponibility)
			return false;
		if (idTrainerDisponibility == null) {
			if (other.idTrainerDisponibility != null)
				return false;
		} else if (!idTrainerDisponibility.equals(other.idTrainerDisponibility))
			return false;
		if (trainer == null) {
			if (other.trainer != null)
				return false;
		} else if (!trainer.equals(other.trainer))
			return false;
		return true;
	}
    
    
}
