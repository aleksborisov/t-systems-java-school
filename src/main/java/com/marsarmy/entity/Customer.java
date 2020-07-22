package com.marsarmy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long id;
    @Column(name = "first_name", length = 45)
    @NotNull
    private String firstName;
    @Column(name = "last_name", length = 45)
    @NotNull
    private String lastName;
    @Column(name = "date_of_birth")
    @NotNull
    private LocalDateTime dateOfBirth;
    @Column(name = "email", unique = true, length = 45)
    @NotNull
    private String email;
    @Column(name = "password", length = 45)
    private String password;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Address> addresses;
    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Order> orders;
}
