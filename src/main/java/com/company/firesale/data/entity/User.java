package com.company.firesale.data.entity;

import com.company.firesale.data.validation_group.UserRegistrationValidationGroup;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"roles", "auctions"})
@Builder
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

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Auction> auctions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Bid> bid = new HashSet<>();

    public User addAuction(Auction auction) {
        if (this.auctions == null) {
            this.auctions = new HashSet<>();
        }
        this.auctions.add(auction);
        return this;
    }

    public User addRole(Role role) {
        roles.add(role);
        return this;
    }

}
