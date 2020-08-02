package com.marsarmy.dao.interf;

import com.marsarmy.model.Product;

import java.util.List;

public interface ProductDao {

    void create(Product product);

    void update(Product product);

    List<Product> getAll();

    Product getOne(long upc);

    List<Product> filter(String category, String name, int minPrice,
                         int maxPrice, String brand, String color);
}
