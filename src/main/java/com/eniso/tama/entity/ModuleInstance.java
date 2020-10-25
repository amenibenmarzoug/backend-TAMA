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
@Table(name="moduleInstance")
public class ModuleInstance {

	@Id
    @Column(name = "module_instance_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Column(name = "moduleName")
	private String moduleInstanceName;
	
	
	@NotNull
	@Column(name="moduleDays")
	private int nbDaysModuleInstance;


	public Long getId() {
		return id;
	}


	public String getModuleInstanceName() {
		return moduleInstanceName;
	}


	public int getNbDaysModuleInstance() {
		return nbDaysModuleInstance;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setModuleInstanceName(String moduleInstanceName) {
		this.moduleInstanceName = moduleInstanceName;
	}


	public void setNbDaysModuleInstance(int nbDaysModuleInstance) {
		this.nbDaysModuleInstance = nbDaysModuleInstance;
	}
	
	
}
