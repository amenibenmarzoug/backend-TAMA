package com.eniso.tama.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
public class ThemeInstance {
    @Id
    @Column(name = "themeInst_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private String themeInstName;


    @NotNull
    @Column
    private int nbDaysthemeInst;
    @ManyToOne
    private Theme theme;
    @ManyToOne(cascade = {CascadeType.ALL})
    private ProgramInstance programInstance;

    public String getThemeInstName() {
        return themeInstName;
    }

    public void setThemeInstName(String themeInstName) {
        this.themeInstName = themeInstName;
    }

	/*@Column(name = "themeInst_begin_date")
	private Date themeInstBeginDate;*/
	
	/*@Column(name = "themeInst_end_date")
	private Date themeInstEndDate;*/

    public int getNbDaysthemeInst() {
        return nbDaysthemeInst;
    }

    public void setNbDaysthemeInst(int nbDaysthemeInst) {
        this.nbDaysthemeInst = nbDaysthemeInst;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	/*public Date getThemeInstBeginDate() {
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
	}*/

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
