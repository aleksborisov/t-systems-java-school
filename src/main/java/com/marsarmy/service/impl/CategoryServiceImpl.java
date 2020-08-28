package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.CategoryDao;
import com.marsarmy.dao.interf.ProductDao;
import com.marsarmy.model.Category;
import com.marsarmy.model.Product;
import com.marsarmy.service.interf.CategoryService;
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

    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    /**
     * Creates a new category
     *
     * @param category Category entity to be created
     */
    @Override
    @Transactional
    public void create(Category category) {
        categoryDao.create(category);
        LOGGER.info("Category \"" + category.getName() + "\" was created");
    }

    /**
     * Updates transmitted category
     *
     * @param category Category entity to be updated
     */
    @Override
    @Transactional
    public void update(Category category) {
        categoryDao.update(category);
        LOGGER.info("Category \"" + category.getName() + "\" was updated");
    }

    /**
     * Deletes transmitted category
     *
     * @param category Category entity to be deleted
     */
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
    public Category getOne(Long id) {
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
        return categoryDao.getByName(name);
    }
}
