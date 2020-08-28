package com.marsarmy.dao.interf;

import com.marsarmy.statistics.ProductStatistics;
import com.marsarmy.model.Product;

import java.util.List;

/**
 * DAO interface of Product entity
 */
public interface ProductDao {

    void create(Product product);

    void update(Product product);

    void flush();

    List<Product> getAll();

    List<Product> getByCategory(String category);

    Product getOne(long upc);

    List<Product> filter(String category, String name, int minPrice,
                         int maxPrice, String brand, String color);

    List<ProductStatistics> getTopTenProducts();
}
