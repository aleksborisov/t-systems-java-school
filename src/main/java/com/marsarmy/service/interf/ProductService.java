package com.marsarmy.service.interf;

import com.marsarmy.model.Product;

import java.util.List;

public interface ProductService {

    void update(Product product);

    List<Product> getAll();

    Product getOne(long upc);

    List<Product> filter(String category, String name, int minPrice,
                         int maxPrice, String brand, String color);
}
