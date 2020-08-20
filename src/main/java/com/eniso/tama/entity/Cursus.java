package com.eniso.tama.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cursus")
public class Cursus {
	
	@Id
    @Column(name = "cursus_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Column(name = "cursusName")
	private String cursusName;
	
	
	@Column(name = "cursus_begin_date")
	private Date cursusBeginDate;
	
	@Column(name = "cursus_end_date")
	private Date cursusEndDate;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCursusName() {
		return cursusName;
	}

	public void setCursusName(String cursusName) {
		this.cursusName = cursusName;
	}

	public Date getCursusBeginDate() {
		return cursusBeginDate;
	}

	public void setCursusBeginDate(Date cursusBeginDate) {
		this.cursusBeginDate = cursusBeginDate;
	}

	public Date getCursusEndDate() {
		return cursusEndDate;
	}

	public void setCursusEndDate(Date cursusEndDate) {
		this.cursusEndDate = cursusEndDate;
	}

	
}
