package com.marsarmy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_upc")
    private long upc;
    @Column(name = "name")
    @NotNull
    private String name;
    @Column(name = "price")
    @NotNull
    private int price;
    @Column(name = "category", length = 45)
    @NotNull
    private String category;
    @Column(name = "brand", length = 45)
    @NotNull
    private String brand;
    @Column(name = "color", length = 45)
    @NotNull
    private String color;
    @Column(name = "weight")
    @NotNull
    private int weight;
    @Column(name = "height")
    @NotNull
    private int height;
    @Column(name = "width")
    @NotNull
    private int width;
    @Column(name = "depth")
    @NotNull
    private int depth;
    @Column(name = "in_stock")
    @NotNull
    private int inStock;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<ProductsInOrder> productsInOrders;         // а нужен ли продукту список заказов?
}
