package com.eniso.tama.entity;

import java.util.Date;

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
	
	@Column(name = "themeDetailInst_begin_date")
	private Date themeDetailInstBeginDate;
	
	@Column(name = "themeDetailInst_end_date")
	private Date themeDetailInstEndDate;
	

}
