package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.CategoryDao;
import com.marsarmy.dao.interf.ProductDao;
import com.marsarmy.model.Category;
import com.marsarmy.model.Product;
import com.marsarmy.service.interf.CategoryService;
import com.marsarmy.service.interf.JmsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service responsible for operations on categories
 */
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;
    private final JmsService jmsService;

    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, ProductDao productDao, JmsService jmsService) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
        this.jmsService = jmsService;
    }

    /**
     * Creates a new category
     *
     * @param category Category entity to be created
     */
    @Override
    @Transactional
    public void create(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null");
        }

        categoryDao.create(category);
        LOGGER.info("Category \"" + category.getName() + "\" was created");
    }

    /**
     * Updates transmitted category and initializes update of statistics in advertising stands
     *
     * @param category Category entity to be updated
     */
    @Override
    @Transactional
    public void update(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null");
        }

        categoryDao.update(category);
        jmsService.sendUpdate();
        LOGGER.info("Category \"" + category.getName() + "\" was updated");
    }

    /**
     * Deletes transmitted category and initializes update of statistics in advertising stands
     *
     * @param category Category entity to be deleted
     */
    @Override
    @Transactional
    public void delete(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null");
        }

        List<Product> products = productDao.getByCategory(category.getName());
        for (Product product : products) {
            product.setCategory(categoryDao.getByName("Other Equipment"));
            productDao.update(product);
            productDao.flush();
        }

        categoryDao.delete(category);
        jmsService.sendUpdate();
        LOGGER.info("Category \"" + category.getName() + "\" was deleted");
    }

    /**
     * Returns the list of all categories
     *
     * @return {@link List} of {@link Category}
     */
    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    /**
     * Returns the category by id
     *
     * @param id Id of category to get
     * @return {@link Category}
     */
    @Override
    public Category getOne(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Category id can't be less than 1");
        }

        return categoryDao.getOne(id);
    }

    /**
     * Returns the category by name
     *
     * @param name Name of category to get
     * @return {@link Category}
     */
    @Override
    public Category getByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be null or empty");
        }

        return categoryDao.getByName(name);
    }
}
