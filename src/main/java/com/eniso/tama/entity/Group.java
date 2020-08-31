package com.eniso.tama.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="groups")
public class Group {
	
	
	@Id
    @Column(name = "group_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
	@NotNull
	@Column(name = "groupName")
	private String groupName;
	
	public String getGroupName() {
		return groupName;
	}



	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public Group() {
	
	}


	public Group(Long id, @NotNull @NotBlank String groupName) {
		//super();
		this.id = id;
		this.groupName = groupName;
	}
	

	
}
