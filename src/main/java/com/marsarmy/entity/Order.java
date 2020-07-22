package com.marsarmy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "payment_method", length = 45)
    @NotNull
    private String paymentMethod;
    @Column(name = "delivery_method", length = 45)
    @NotNull
    private String deliveryMethod;
    @Column(name = "payment_status", length = 45)
    @NotNull
    private String paymentStatus;
    @Column(name = "order_status", length = 45)
    @NotNull
    private String orderStatus;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<ProductsInOrder> productsInOrders;
}
