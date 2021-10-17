package com.eniso.tama.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ThemeDetailInstance {

    @Id
    @Column(name = "themeDetailInstance_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private ThemeDetail themeDetail;

    @ManyToOne(cascade = {CascadeType.ALL})
    private ModuleInstance moduleInstance;

    @Column
    private String themeDetailInstName;

    @Column
    private int nbDaysthemeDetailInst;


    public String getThemeDetailInstName() {
        return themeDetailInstName;
    }

    public void setThemeDetailInstName(String themeDetailInstName) {
        this.themeDetailInstName = themeDetailInstName;
    }

    public int getNbDaysthemeDetailInst() {
        return nbDaysthemeDetailInst;
    }

    public void setNbDaysthemeDetailInst(int nbDaysthemeDetailInst) {
        this.nbDaysthemeDetailInst = nbDaysthemeDetailInst;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ThemeDetail getThemeDetail() {
        return themeDetail;
    }

    public void setThemeDetail(ThemeDetail themeDetail) {
        this.themeDetail = themeDetail;
    }

	

    public ModuleInstance getModuleInstance() {
        return moduleInstance;
    }

    public void setModuleInstance(ModuleInstance moduleInstance) {
        this.moduleInstance = moduleInstance;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((moduleInstance == null) ? 0 : moduleInstance.hashCode());
		result = prime * result + nbDaysthemeDetailInst;
		result = prime * result + ((themeDetail == null) ? 0 : themeDetail.hashCode());
		result = prime * result + ((themeDetailInstName == null) ? 0 : themeDetailInstName.hashCode());
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
		ThemeDetailInstance other = (ThemeDetailInstance) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (moduleInstance == null) {
			if (other.moduleInstance != null)
				return false;
		} else if (!moduleInstance.equals(other.moduleInstance))
			return false;
		if (nbDaysthemeDetailInst != other.nbDaysthemeDetailInst)
			return false;
		if (themeDetail == null) {
			if (other.themeDetail != null)
				return false;
		} else if (!themeDetail.equals(other.themeDetail))
			return false;
		if (themeDetailInstName == null) {
			if (other.themeDetailInstName != null)
				return false;
		} else if (!themeDetailInstName.equals(other.themeDetailInstName))
			return false;
		return true;
	}
    
    



}
