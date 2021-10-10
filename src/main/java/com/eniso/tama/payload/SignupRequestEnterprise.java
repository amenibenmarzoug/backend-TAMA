package com.eniso.tama.payload;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.eniso.tama.entity.ProgramInstance;


public class SignupRequestEnterprise {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;


    //	@NotBlank
//	@Size(min = 3, max = 20)
//	private String address;
    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String postalCode;
    @NotBlank
    @Size(min = 3, max = 20)
    private String phoneNumber;
    @NotBlank
    @Size(min = 8, max = 40)
    private String password;
    //@NotNull
    private String enterpriseName;
    private String website;
    private String managerFirstName;
    private String managerLastName;
    private String managerPosition;
    private int nbMinParticipants;
    private Set<String> role;
    private ProgramInstance programInstance;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public ProgramInstance getProgramInstance() {
        return programInstance;
    }

    public void setProgramInstance(ProgramInstance programInstance) {
        this.programInstance = programInstance;
    }


    public String getManagerPosition() {
        return managerPosition;
    }

    public void setManagerPosition(String managerPosition) {
        this.managerPosition = managerPosition;
    }

    public int getNbMinParticipants() {
        return nbMinParticipants;
    }

    public void setNbMinParticipants(int nbMinParticipants) {
        this.nbMinParticipants = nbMinParticipants;
    }

    @Override
    public String toString() {
        return "SignupRequestEnterprise [email=" + email + ", street=" + street + ", city=" + city + ", postalCode="
                + postalCode + ", phoneNumber=" + phoneNumber + ", password=" + password + ", enterpriseName="
                + enterpriseName + ", website=" + website + ", Manager's First Name=" + managerFirstName +
                ", Manager's Last Name=" + managerLastName + ", role=" + role + "]";
    }


}
