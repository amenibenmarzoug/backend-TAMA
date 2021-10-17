package com.eniso.tama.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
public class ModuleInstance {

    @Id
    @Column(name = "module_instance_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "moduleName")
    private String moduleInstanceName;


    @NotNull
    @Column(name = "moduleDays")
    private int nbDaysModuleInstance;


    @ManyToOne
    private Module module;


    @ManyToOne(cascade = {CascadeType.ALL})
    private ThemeInstance themeInstance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleInstanceName() {
        return moduleInstanceName;
    }

    public void setModuleInstanceName(String moduleInstanceName) {
        this.moduleInstanceName = moduleInstanceName;
    }

    public int getNbDaysModuleInstance() {
        return nbDaysModuleInstance;
    }

    public void setNbDaysModuleInstance(int nbDaysModuleInstance) {
        this.nbDaysModuleInstance = nbDaysModuleInstance;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public ThemeInstance getThemeInstance() {
        return themeInstance;
    }

    public void setThemeInstance(ThemeInstance themeInstance) {
        this.themeInstance = themeInstance;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((moduleInstanceName == null) ? 0 : moduleInstanceName.hashCode());
		result = prime * result + nbDaysModuleInstance;
		result = prime * result + ((themeInstance == null) ? 0 : themeInstance.hashCode());
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
		ModuleInstance other = (ModuleInstance) obj;
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
		if (moduleInstanceName == null) {
			if (other.moduleInstanceName != null)
				return false;
		} else if (!moduleInstanceName.equals(other.moduleInstanceName))
			return false;
		if (nbDaysModuleInstance != other.nbDaysModuleInstance)
			return false;
		if (themeInstance == null) {
			if (other.themeInstance != null)
				return false;
		} else if (!themeInstance.equals(other.themeInstance))
			return false;
		return true;
	}
    
    


}
