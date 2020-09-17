package com.eniso.tama.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="classroom")
public class ClassRoom {
	@Id
    @Column(name = "classRoom_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	
	@NotNull
	@Column(name = "classRoomName")
	private String classRoomName;
	
//	
//	@Column(name="disponibility")
//	private boolean disponibility;
	
	@Column(name="capacity")
	private int capacity;
	
//	@OneToMany(mappedBy="classroom" ,cascade = {CascadeType.ALL})
//	private Set<Equipments> equipements;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Set<Equipments> getEquipements() {
//		return equipements;
//	}
//
//	public void setEquipements(Set<Equipments> equipements) {
//		this.equipements = equipements;
//	}

	public String getClassRoomName() {
		return classRoomName;
	}

	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}

//	public boolean isDisponibility() {
//		return disponibility;
//	}
//
//	public void setDisponibility(boolean disponibility) {
//		this.disponibility = disponibility;
//	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@ManyToOne
	private Institution institution;

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	
}
