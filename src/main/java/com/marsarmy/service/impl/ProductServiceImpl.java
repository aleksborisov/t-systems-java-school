package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.ProductDao;
import com.marsarmy.model.Product;
import com.marsarmy.service.interf.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
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
}
