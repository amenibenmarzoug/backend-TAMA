package com.eniso.tama.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.*;

import org.springframework.data.annotation.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
//@MappedSuperclass
public class User {
    @Id
    @Column(name = "user_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // this variable is for validating the accounts
    // @NotNull
    @Column
    private Boolean validated = false;
    @NotNull
    @Column
    @Email(message = "{errors.invalid_email}")
    private String email;
    @NotNull
    @Column( insertable = true, updatable = true, nullable = false)
    // @Size(min=8, max=40)
    private String password;
    @NotNull
    @Column
    private String street;
    @NotNull
    @Column
    private String city;
    @NotNull
    @Column
    private String postalCode;

    @Column
    private String phoneNumber;

    @CreatedDate
    @Column
    @JsonIgnore
    private Instant createdDate = Instant.now();

    @LastModifiedDate
    @Column
    @JsonIgnore
    private Instant lastModifiedDate = Instant.now();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(@NotBlank @Size(max = 50) @Email String email, String password, @NotBlank String street,
                @NotBlank String city, @NotBlank String postalCode, @NotNull String phoneNumber, Set<Role> roles) {
        // super();
        this.email = email;
        this.password = password;
        // this.address = address;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public User(String email, String password, Boolean validated) {
        this.email = email;
        this.password = password;
        this.validated = false;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", validated=" + validated + ", email=" + email + ", password=" + password
                + ", street=" + street + ", city=" + city + ", postalCode=" + postalCode + ", phoneNumber="
                + phoneNumber + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", roles="
                + roles + "]";
    }

    public Boolean isValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}