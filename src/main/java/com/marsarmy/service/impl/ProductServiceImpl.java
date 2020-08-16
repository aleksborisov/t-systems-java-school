package com.marsarmy.service.impl;

import com.marsarmy.converter.ProductConverter;
import com.marsarmy.dao.interf.ProductDao;
import com.marsarmy.dto.ProductDto;
import com.marsarmy.statistics.ProductStatistics;
import com.marsarmy.model.Product;
import com.marsarmy.service.interf.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final ProductConverter productConverter;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, ProductConverter productConverter) {
        this.productDao = productDao;
        this.productConverter = productConverter;
    }

    @Override
    @Transactional
    public void create(Product product) {
        productDao.create(product);
    }

    @Override
    @Transactional
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product getOne(long upc) {
        return productDao.getOne(upc);
    }

    @Override
    public List<Product> filter(String category, String name, int minPrice,
                                int maxPrice, String brand, String color) {

        return productDao.filter(category, name, minPrice, maxPrice, brand, color);
    }

    @Override
    public List<ProductStatistics> getTopTenProducts() {
        return productDao.getTopTenProducts();
    }

    @Override
    public Map<ProductDto, Integer> getProductsFromCart(Map<Long, Integer> cart) {
        if (cart == null) {
            return new HashMap<>();
        }

        Map<ProductDto, Integer> products = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            products.put(productConverter.convertToDto(productDao.getOne(entry.getKey())), entry.getValue());
        }

        return products;
    }

    @Override
    public boolean checkNumberOfProducts(long upc, int quantity) {
        return productDao.getOne(upc).getInStock() >= quantity;
    }
}
