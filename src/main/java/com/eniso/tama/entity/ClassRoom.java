package com.eniso.tama.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ClassRoom {
	
    @Id
    @Column(name = "classRoom_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column
    private String classRoomName;

    @Column
    private int capacity;

    @Column
    private int fees;


    @ManyToOne
    private Institution institution;

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

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((classRoomName == null) ? 0 : classRoomName.hashCode());
		result = prime * result + fees;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((institution == null) ? 0 : institution.hashCode());
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
		ClassRoom other = (ClassRoom) obj;
		if (capacity != other.capacity)
			return false;
		if (classRoomName == null) {
			if (other.classRoomName != null)
				return false;
		} else if (!classRoomName.equals(other.classRoomName))
			return false;
		if (fees != other.fees)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (institution == null) {
			if (other.institution != null)
				return false;
		} else if (!institution.equals(other.institution))
			return false;
		return true;
	}
    
    


}