package com.eniso.tama.entity;


import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.*;

import org.springframework.data.annotation.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name="user")
//@MappedSuperclass
public class User {
	
	@Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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


	@NotNull
	@Column(name = "email")
	@Email(message="{errors.invalid_email}")
	private String email;
	@NotNull
	@Column(name = "password")
	@Size(min=8)
	private String password;
	@NotNull
	@Column(name = "address")
	private String address;
	
	@NotNull
	@Column(name = "phoneNumber")
	private String phoneNumber;  
	
	
	
	@CreatedDate 
	@Column(name = "created_date")
	@JsonIgnore
	private Instant createdDate = Instant.now();
	

	@LastModifiedDate 
	@Column(name = "last_modified_date")
	@JsonIgnore
	private Instant lastModifiedDate = Instant.now();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(	name = "user_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	

	


	

	
	
}
