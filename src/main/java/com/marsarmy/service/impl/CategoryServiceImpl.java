package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.CategoryDao;
import com.marsarmy.dao.interf.ProductDao;
import com.marsarmy.model.Category;
import com.marsarmy.model.Product;
import com.marsarmy.service.interf.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void create(Category category) {
        categoryDao.create(category);
    }

    @Override
    @Transactional
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    @Transactional
    public void delete(Category category) {
        List<Product> products = productDao.getByCategory(category.getName());
        for (Product product : products) {
            product.setCategory(categoryDao.getByName("Other Equipment"));
            productDao.update(product);
            productDao.flush();
        }

        categoryDao.delete(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public Category getOne(Long id) {
        return categoryDao.getOne(id);
    }

    @Override
    public Category getByName(String name) {
        return categoryDao.getByName(name);
    }
}
