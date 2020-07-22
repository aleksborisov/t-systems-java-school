package com.marsarmy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products_in_orders")
@Data
@NoArgsConstructor
public class ProductsInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "products_in_order_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_upc")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(name = "number_of_products")
    @NotNull
    private int numberOfProducts;
}
