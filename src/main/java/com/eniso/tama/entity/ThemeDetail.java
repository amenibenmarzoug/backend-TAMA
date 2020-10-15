package com.eniso.tama.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="course")
public class Course {
	@Id
    @Column(name = "course_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Column(name = "courseName")
	private String courseName;
	
	
	@Column(name="fees")
	private double fees;
	
	@Column(name="nb_max_participants")
	private int nbmaxParticipants;
	
	@NotNull
	@Column(name = "theme")
	private String theme;
	
	
	@Lob
	@Column(name = "content")
	private String content;


	//Getters and Setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public double getFees() {
		return fees;
	}


	public void setFees(double fees) {
		this.fees = fees;
	}


	public int getNbmaxParticipants() {
		return nbmaxParticipants;
	}


	public void setNbmaxParticipants(int nbmaxParticipants) {
		this.nbmaxParticipants = nbmaxParticipants;
	}


	public String getTheme() {
		return theme;
	}


	public void setTheme(String theme) {
		this.theme = theme;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	
	@ManyToOne
	private Cursus cursus;

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}
	
	@ManyToOne
	private Trainer trainer;


	public Trainer getTrainer() {
		return trainer;
	}


	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	
	

}
