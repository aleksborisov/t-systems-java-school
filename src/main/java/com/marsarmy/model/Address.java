package com.marsarmy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "country", length = 50, nullable = false)
    @NotBlank
    @Size(min = 3, max = 50)
    private String country;

    @Column(name = "city", nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String city;

    @Column(name = "zip_code", length = 9, nullable = false)
    @NotBlank
    @Size(min = 3, max = 9)
    private String zipCode;

    @Column(name = "street", nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String street;

    @Column(name = "building", nullable = false)
    @Min(1)
    private int building;

    @Column(name = "apartment", nullable = false)
    @Min(1)
    private int apartment;

    @OneToMany(mappedBy = "address",
            cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> orders;
}
