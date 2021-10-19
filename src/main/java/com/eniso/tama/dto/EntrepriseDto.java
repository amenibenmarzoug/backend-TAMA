package com.eniso.tama.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.eniso.tama.entity.CompanyRegistration;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EntrepriseDto {
	  private long id;
	    private String enterpriseName;

	    private String managerFirstName;

	    private String managerLastName;
	    private String managerPosition;

	    private String website;

	    private int nbMinParticipants;
	    
	    private boolean provider=false;

	    private Set<ProgramInstance> programInstance;

	    private Boolean validated = false;
	   
	    private String email;
	 
	    private String street;
	
	    private String city;
	 
	    private String postalCode;

	    private String phoneNumber;

	    private Instant createdDate = Instant.now();

	    private Instant lastModifiedDate = Instant.now();
	    
	    private Set<Role> roles = new HashSet<>();
	    
	    
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public Boolean getValidated() {
			return validated;
		}

		public void setValidated(Boolean validated) {
			this.validated = validated;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

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

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public Instant getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Instant createdDate) {
			this.createdDate = createdDate;
		}

		public Instant getLastModifiedDate() {
			return lastModifiedDate;
		}

		public void setLastModifiedDate(Instant lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
		}

		public String getEnterpriseName() {
			return enterpriseName;
		}

		public void setEnterpriseName(String enterpriseName) {
			this.enterpriseName = enterpriseName;
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

		public String getManagerPosition() {
			return managerPosition;
		}

		public void setManagerPosition(String managerPosition) {
			this.managerPosition = managerPosition;
		}

		public String getWebsite() {
			return website;
		}

		public void setWebsite(String website) {
			this.website = website;
		}

		public int getNbMinParticipants() {
			return nbMinParticipants;
		}

		public void setNbMinParticipants(int nbMinParticipants) {
			this.nbMinParticipants = nbMinParticipants;
		}

		public boolean isProvider() {
			return provider;
		}

		public void setProvider(boolean provider) {
			this.provider = provider;
		}

	
		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}

		
		
		public Set<ProgramInstance> getProgramInstance() {
			return programInstance;
		}

		public void setProgramInstance(Set<ProgramInstance> programInstance) {
			this.programInstance = programInstance;
		}

		public EntrepriseDto(String enterpriseName, String managerFirstName, String managerLastName,
				String managerPosition, String website, int nbMinParticipants, boolean provider,
				Set<ProgramInstance> classes, Boolean validated, String email, String street, String city,
				String postalCode, String phoneNumber, Instant createdDate, Instant lastModifiedDate, Set<Role> roles) {
			super();
			this.enterpriseName = enterpriseName;
			this.managerFirstName = managerFirstName;
			this.managerLastName = managerLastName;
			this.managerPosition = managerPosition;
			this.website = website;
			this.nbMinParticipants = nbMinParticipants;
			this.provider = provider;
			this.programInstance = classes;
			this.validated = validated;
			this.email = email;
			this.street = street;
			this.city = city;
			this.postalCode = postalCode;
			this.phoneNumber = phoneNumber;
			this.createdDate = createdDate;
			this.lastModifiedDate = lastModifiedDate;
			this.roles = roles;
		}

		public EntrepriseDto() {
			super();
		}

	

	    

}
