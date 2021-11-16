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
public class ThemeDetail {
    @Id
    @Column(name = "themeDetail_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private String themeDetailName;

    @NotNull
    @Column(name = "themeDetailDays")
    private int nbDaysThemeDetail;
    
    private boolean deleted;

    @ManyToOne
    private Module module;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThemeDetailName() {
        return themeDetailName;
    }

    public void setThemeDetailName(String themeDetailName) {
        this.themeDetailName = themeDetailName;
    }

    public int getNbDaysThemeDetail() {
        return nbDaysThemeDetail;
    }

    public void setNbDaysThemeDetail(int nbDaysThemeDetail) {
        this.nbDaysThemeDetail = nbDaysThemeDetail;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


}
