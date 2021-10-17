package com.eniso.tama.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Theme {

    @Id
    @Column(name = "theme_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String themeName;

    @NotNull
    @Column(name = "themeDays")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + nbDaysTheme;
		result = prime * result + ((program == null) ? 0 : program.hashCode());
		result = prime * result + ((themeName == null) ? 0 : themeName.hashCode());
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
		Theme other = (Theme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nbDaysTheme != other.nbDaysTheme)
			return false;
		if (program == null) {
			if (other.program != null)
				return false;
		} else if (!program.equals(other.program))
			return false;
		if (themeName == null) {
			if (other.themeName != null)
				return false;
		} else if (!themeName.equals(other.themeName))
			return false;
		return true;
	}
    
    


}
