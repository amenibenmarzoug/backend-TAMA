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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private String programName;

    @NotNull
    @Column(name = "ProgramDays")
    private int nbDaysProg;

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
}
