package com.eniso.tama.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ThemeDetailInstance")
public class ThemeDetailInstance {
	
	@Id
    @Column(name = "themeDetailInstance_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	private ThemeDetail themeDetail;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	private ModuleInstance moduleInstance;
	
	/*@Column(name = "themeDetailInst_begin_date")
	private Date themeDetailInstBeginDate;*/
	
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

	/*public Date getThemeDetailInstBeginDate() {
		return themeDetailInstBeginDate;
	}

	public void setThemeDetailInstBeginDate(Date themeDetailInstBeginDate) {
		this.themeDetailInstBeginDate = themeDetailInstBeginDate;
	}*/

	/*public Date getThemeDetailInstEndDate() {
		return themeDetailInstEndDate;
	}

	public void setThemeDetailInstEndDate(Date themeDetailInstEndDate) {
		this.themeDetailInstEndDate = themeDetailInstEndDate;
	}*/

	
	public ModuleInstance getModuleInstance() {
		return moduleInstance;
	}

	public void setModuleInstance(ModuleInstance moduleInstance) {
		this.moduleInstance = moduleInstance;
	}


	/*@Column(name = "themeDetailInst_end_date")
	private Date themeDetailInstEndDate;*/
	

}
