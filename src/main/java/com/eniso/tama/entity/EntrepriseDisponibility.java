package com.eniso.tama.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity

public class EntrepriseDisponibility {
	
	@Id
    @Column(name = "EntrepriseDispo_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	
	@Column(name = "day")
	private String day;

	@Column(name = "time")
	private String time ;
  
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "Entre_id")
	Entreprise entreprise;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

}
