package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.CategoryDao;
import com.marsarmy.model.Category;
import com.marsarmy.service.interf.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
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
