package com.eniso.tama.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
public class Program {
	
    @Id
    @Column(name = "program_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String programName;

    @NotNull
    @Column(name = "ProgramDays")
    private int nbDaysProg;
    
    private boolean specificProgram =false ; 

    public Long getId() {
        return id;
    }

	public void setId(Long id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getNbDaysProg() {
        return nbDaysProg;
    }

    public void setNbDaysProg(int nbDaysProg) {
        this.nbDaysProg = nbDaysProg;
    }
    
    public boolean isSpecificProgram() {
		return specificProgram;
	}

	public void setSpecificProgram(boolean specificProgram) {
		this.specificProgram = specificProgram;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + nbDaysProg;
		result = prime * result + ((programName == null) ? 0 : programName.hashCode());
		result = prime * result + (specificProgram ? 1231 : 1237);
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
		Program other = (Program) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nbDaysProg != other.nbDaysProg)
			return false;
		if (programName == null) {
			if (other.programName != null)
				return false;
		} else if (!programName.equals(other.programName))
			return false;
		if (specificProgram != other.specificProgram)
			return false;
		return true;
	}
	
	
	
}
