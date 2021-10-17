package com.eniso.tama.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ClassRoom {
    @Id
    @Column(name = "classRoom_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull
    @Column
    private String classRoomName;

//
//	@Column(name="disponibility")
//	private boolean disponibility;

    @Column
    private int capacity;

    @Column
    private int fees;


    @ManyToOne
    private Institution institution;

//	@OneToMany(mappedBy="classroom" ,cascade = {CascadeType.ALL})
//	private Set<Equipments> equipements;

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

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


    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }


}