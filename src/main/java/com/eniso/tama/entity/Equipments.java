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
@Table(name="equipments")
public class Equipments {
	@Id
    @Column(name = "equipment_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	//@NotNull
	@Column(name = "equipmentName")
	private String equipmentName;
	
	
	@Column(name = "quantity")
	private int quantity;
	
	public ClassRoom getClassroom() {
		return classroom;
	}

	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	private ClassRoom classroom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	
	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Equipments [id=" + id + ", equipmentName=" + equipmentName + ", quantity=" + quantity + ", classroom="
				+ classroom + "]";
	}
	
	
	
	
}
