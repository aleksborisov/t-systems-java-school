package com.marsarmy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "country", length = 45)
    @NotNull
    private String country;
    @Column(name = "city", length = 45)
    @NotNull
    private String city;
    @Column(name = "zip_code", length = 45)
    @NotNull
    private String zipCode;
    @Column(name = "street", length = 45)
    @NotNull
    private String street;
    @Column(name = "building")
    @NotNull
    private int building;
    @Column(name = "apartment")
    @NotNull
    private int apartment;

    @OneToMany(mappedBy = "address",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Order> orders;         // а нужен ли адресу список заказов?
}
