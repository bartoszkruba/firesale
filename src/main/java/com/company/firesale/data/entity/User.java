package com.company.firesale.data.entity;

import com.company.firesale.data.validation_group.UserRegistrationValidationGroup;
import com.company.firesale.entity.Auction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotEmpty(groups = UserRegistrationValidationGroup.class)
    private String username;

    @NotEmpty(groups = UserRegistrationValidationGroup.class)
    private String password;

    @NotEmpty(groups = UserRegistrationValidationGroup.class)
    @Email(groups = UserRegistrationValidationGroup.class)
    private String email;

    @NotEmpty(groups = UserRegistrationValidationGroup.class)
    private String address;

    @NotEmpty(groups = UserRegistrationValidationGroup.class)
    private String phoneNumber;

    @NotEmpty(groups = UserRegistrationValidationGroup.class)
    private String firstName;

    @NotEmpty(groups = UserRegistrationValidationGroup.class)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Auction> auctions;

    public User() {
    }

    public User(String username, String password,
                String email, String address,
                String phoneNumber, String firstName,
                String lastName) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User addAuction(Auction auction) {
        if (this.auctions == null) {
            this.auctions = new HashSet<>();
        }
        this.auctions.add(auction);
        return this;
    }

    public User addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
        return this;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(Set<Auction> auctions) {
        this.auctions = auctions;
    }
}
