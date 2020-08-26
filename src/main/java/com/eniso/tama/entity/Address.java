package com.eniso.tama.entity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Embeddable
public class Address {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	private String street;
	 private String city;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	private String state;
	 private String postalCode;
	
	public Address() {

    }
	 public Address( String street,  String city, String state,  String postalCode) {
			this.street = street;
			this.city = city;
			this.state = state;
			this.postalCode = postalCode;
		}

}
