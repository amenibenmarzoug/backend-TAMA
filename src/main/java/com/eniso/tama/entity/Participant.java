package com.eniso.tama.entity;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Participant extends User {


    @JsonIgnore
    @OneToMany(mappedBy = "participant")
    Set<SessionParticipant> sessionParticipant;
    @NotNull
    @Column
    private String firstNameP;
    @NotNull
    @Column
    private String lastNameP;
    @NotNull
    @Column(name = "gender")
    private String gender;
    @NotNull
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "currentPosition")
    private String currentPosition;
    @Column(name = "level")
    private String level;
    @Column(name = "educationLevel")
    private String educationLevel;
    @NotNull
    @Column(name = "abandon")
    private boolean abandon;
    @ManyToOne
    //@JsonIgnore
    private Entreprise entreprise;
    @ManyToOne
    //@JsonIgnore
    private ProgramInstance programInstance;

    public Participant() {

    }


    public Participant(@NotBlank @Size(max = 50) @Email String email,
                       String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber, Set<Role> roles, @NotBlank @Size(max = 20) String firstName,
                       @NotBlank String lastName, @NotBlank String gender, Date birthday) {
        //this.setId(super.getId());
        super.setEmail(email);
        super.setPassword(password);
        //super.setAddress(address);
        super.setStreet(street);
        super.setCity(city);
        super.setPostalCode(postalCode);
        super.setPhoneNumber(phoneNumber);
        super.setRoles(roles);
        this.firstNameP = firstName;
        this.lastNameP = lastName;
        this.gender = gender;
        this.birthday = birthday;

    }

    public Participant(@NotBlank @Size(max = 50) @Email String email,
                       String password, @NotBlank String street, @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber, Set<Role> roles, @NotBlank @Size(max = 20) String firstNameP,
                       @NotBlank String lastNameP, @NotBlank String gender, Date birthday, Entreprise entreprise, ProgramInstance programInstance) {
        this.setId(super.getId());
        super.setEmail(email);
        super.setPassword(password);
        //super.setAddress(address);
        super.setStreet(street);
        super.setCity(city);
        super.setPostalCode(postalCode);
        super.setPhoneNumber(phoneNumber);
        super.setRoles(roles);
        this.firstNameP = firstNameP;
        this.lastNameP = lastNameP;
        this.gender = gender;
        this.birthday = birthday;
        this.entreprise = entreprise;
        this.programInstance = programInstance;

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isAbandon() {
        return abandon;
    }

    public void setAbandon(boolean abandon) {
        this.abandon = abandon;
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

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public ProgramInstance getProgramInstance() {
        return programInstance;
    }

    public void setProgramInstance(ProgramInstance programInstance) {
        this.programInstance = programInstance;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Set<SessionParticipant> getSessionParticipant() {
        return sessionParticipant;
    }

    public void setSessionParticipant(Set<SessionParticipant> sessionParticipant) {
        this.sessionParticipant = sessionParticipant;
    }

    @Override
    public String toString() {
        return "Participant [ firstNameP=" + firstNameP + ", lastNameP=" + lastNameP
                + ", gender=" + gender + ", birthday=" + birthday + ", currentPosition=" + currentPosition + ", level="
                + level + ", educationLevel=" + educationLevel + ", abandon=" + abandon + ", entreprise=" + entreprise
                + ", courseSessionParticipant=" + sessionParticipant + "]";
    }


}