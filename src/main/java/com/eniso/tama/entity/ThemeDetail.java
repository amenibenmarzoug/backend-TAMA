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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + nbDaysThemeDetail;
		result = prime * result + ((themeDetailName == null) ? 0 : themeDetailName.hashCode());
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
		ThemeDetail other = (ThemeDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (nbDaysThemeDetail != other.nbDaysThemeDetail)
			return false;
		if (themeDetailName == null) {
			if (other.themeDetailName != null)
				return false;
		} else if (!themeDetailName.equals(other.themeDetailName))
			return false;
		return true;
	}
    
    


}
