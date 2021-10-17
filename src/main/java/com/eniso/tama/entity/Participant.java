package com.eniso.tama.entity;

import java.util.*;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Participant extends User{

    @NotNull
    @Column
    private String firstNameP;

    @NotNull
    @Column
    private String lastNameP;

    @NotNull
    @Column
    private String gender;

    @NotNull
    @Column
    private LocalDate birthday;

    @Column
    private String currentPosition;

    @NotNull
    @Column
    private int experience;
    
    @Column
    private String level;

    @Column
    private String educationLevel;
    
    @NotNull
    @Column
    private boolean abandon;

    @ManyToOne
    //@JsonIgnore
    private Entreprise entreprise;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participant")
    private List<ParticipantRegistration> participantRegistrations; 
    
    @Enumerated(EnumType.STRING)
	private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "participant")
    Set<Attendance> attendance;

    
    @Formula(value="YEAR(CURDATE()) - YEAR(BIRTHDAY)")
    private int age;
    
    public Participant() {

    }

    public Participant(@NotBlank @Size(max = 50) @Email String email,
                       String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber,Set<Role> roles,@NotBlank @Size(max = 20)  String firstName,
                       @NotBlank String lastName, @NotBlank String gender , LocalDate birthday) {
        super.setEmail(email);
        super.setPassword(password);
        super.setStreet(street);
        super.setCity(city);
        super.setPostalCode(postalCode);
        super.setPhoneNumber(phoneNumber);
        super.setRoles(roles);
        this.firstNameP = firstName;
        this.lastNameP  = lastName;
        this.gender = gender;
        this.birthday= birthday;

    }


    public Participant(@NotNull String firstNameP, @NotNull String lastNameP, @NotNull String gender,
			@NotNull LocalDate birthday, String currentPosition, @NotNull int experience, int age, String level,
			String educationLevel, @NotNull boolean abandon, Entreprise entreprise,
			List<ParticipantRegistration> participantRegistrations, Status status, Set<Attendance> attendance) {
		super();
		this.firstNameP = firstNameP;
		this.lastNameP = lastNameP;
		this.gender = gender;
		this.birthday = birthday;
		this.currentPosition = currentPosition;
		this.experience = experience;
		this.age = age;
		this.level = level;
		this.educationLevel = educationLevel;
		this.abandon = abandon;
		this.entreprise = entreprise;
		this.participantRegistrations = participantRegistrations;
		this.status = status;
		this.attendance = attendance;
	}


	public String getFirstNameP() {
		return firstNameP;
	}

	public void setFirstNameP(String firstNameP) {
		this.firstNameP = firstNameP;
	}

	public String getLastNameP() {
		return lastNameP;
	}

	public void setLastNameP(String lastNameP) {
		this.lastNameP = lastNameP;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public boolean isAbandon() {
		return abandon;
	}

	public void setAbandon(boolean abandon) {
		this.abandon = abandon;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public List<ParticipantRegistration> getParticipantRegistrations() {
		return participantRegistrations;
	}

	public void setParticipantRegistrations(List<ParticipantRegistration> participantRegistrations) {
		this.participantRegistrations = participantRegistrations;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(Set<Attendance> attendance) {
		this.attendance = attendance;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (abandon ? 1231 : 1237);
		result = prime * result + age;
		result = prime * result + ((attendance == null) ? 0 : attendance.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((currentPosition == null) ? 0 : currentPosition.hashCode());
		result = prime * result + ((educationLevel == null) ? 0 : educationLevel.hashCode());
		result = prime * result + ((entreprise == null) ? 0 : entreprise.hashCode());
		result = prime * result + experience;
		result = prime * result + ((firstNameP == null) ? 0 : firstNameP.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastNameP == null) ? 0 : lastNameP.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((participantRegistrations == null) ? 0 : participantRegistrations.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		if (abandon != other.abandon)
			return false;
		if (age != other.age)
			return false;
		if (attendance == null) {
			if (other.attendance != null)
				return false;
		} else if (!attendance.equals(other.attendance))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (currentPosition == null) {
			if (other.currentPosition != null)
				return false;
		} else if (!currentPosition.equals(other.currentPosition))
			return false;
		if (educationLevel == null) {
			if (other.educationLevel != null)
				return false;
		} else if (!educationLevel.equals(other.educationLevel))
			return false;
		if (entreprise == null) {
			if (other.entreprise != null)
				return false;
		} else if (!entreprise.equals(other.entreprise))
			return false;
		if (experience != other.experience)
			return false;
		if (firstNameP == null) {
			if (other.firstNameP != null)
				return false;
		} else if (!firstNameP.equals(other.firstNameP))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (lastNameP == null) {
			if (other.lastNameP != null)
				return false;
		} else if (!lastNameP.equals(other.lastNameP))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (participantRegistrations == null) {
			if (other.participantRegistrations != null)
				return false;
		} else if (!participantRegistrations.equals(other.participantRegistrations))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Participant [ firstNameP=" + firstNameP + ", lastNameP=" + lastNameP
                + ", gender=" + gender + ", birthday=" + birthday + ", currentPosition=" + currentPosition + ", level="
                + level + ", educationLevel=" + educationLevel + ", abandon=" + abandon + ", entreprise=" + entreprise
                + ", courseSessionParticipant=" + attendance + "]";
    }



}