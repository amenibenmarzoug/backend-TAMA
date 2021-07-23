package com.eniso.tama.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="theme")
public class Theme {
	
	@Id
    @Column(name = "theme_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Column
	private String themeName;
	
	
	@NotNull
	@Column(name="themeDays")
	private int nbDaysTheme;
	
	@ManyToOne
	private Program program;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public int getNbDaysTheme() {
		return nbDaysTheme;
	}

	public void setNbDaysTheme(int nbDaysTheme) {
		this.nbDaysTheme = nbDaysTheme;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	
}
