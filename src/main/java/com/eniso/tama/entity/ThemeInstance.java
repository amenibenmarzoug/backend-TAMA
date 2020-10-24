package com.eniso.tama.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ThemeInstance")
public class ThemeInstance {
	@Id
    @Column(name = "themeInst_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	
	@Column(name = "themeInst_begin_date")
	private Date themeInstBeginDate;
	
	@Column(name = "themeInst_end_date")
	private Date themeInstEndDate;
	
	@ManyToOne
	private Theme theme;
	
	@ManyToOne
	private ProgramInstance programInstance;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getThemeInstBeginDate() {
		return themeInstBeginDate;
	}

	public void setThemeInstBeginDate(Date themeInstBeginDate) {
		this.themeInstBeginDate = themeInstBeginDate;
	}

	public Date getThemeInstEndDate() {
		return themeInstEndDate;
	}

	public void setThemeInstEndDate(Date themeInstEndDate) {
		this.themeInstEndDate = themeInstEndDate;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public ProgramInstance getProgramInstance() {
		return programInstance;
	}

	public void setProgramInstance(ProgramInstance programInstance) {
		this.programInstance = programInstance;
	}
	
	

}
