package com.eniso.tama.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + nbDaysthemeInst;
		result = prime * result + ((programInstance == null) ? 0 : programInstance.hashCode());
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		result = prime * result + ((themeInstName == null) ? 0 : themeInstName.hashCode());
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
		ThemeInstance other = (ThemeInstance) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nbDaysthemeInst != other.nbDaysthemeInst)
			return false;
		if (programInstance == null) {
			if (other.programInstance != null)
				return false;
		} else if (!programInstance.equals(other.programInstance))
			return false;
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		if (themeInstName == null) {
			if (other.themeInstName != null)
				return false;
		} else if (!themeInstName.equals(other.themeInstName))
			return false;
		return true;
	}
    
    


}
