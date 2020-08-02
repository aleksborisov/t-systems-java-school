package com.marsarmy.model;

import com.marsarmy.model.enumeration.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "role_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(name = "first_name", length = 50, nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "email", length = 50, unique = true, nullable = false)
    @NotBlank
    @Email
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    @NotBlank
    @Size(min = 6, max = 50)
    @Pattern(regexp = "^\\S+$")
    private String password;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> orders;

    @Override
    public String toString() {
        return "id = " + id +
                ", role = " + role +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", dateOfBirth = " + dateOfBirth +
                ", email = " + email +
                ", password = " + password;
    }
}
