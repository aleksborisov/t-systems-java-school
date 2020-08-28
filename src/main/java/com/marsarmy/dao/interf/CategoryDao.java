package com.marsarmy.dao.interf;

import com.marsarmy.model.Category;

import java.util.List;

/**
 * DAO interface of Category entity
 */
public interface CategoryDao {

    void create(Category category);

    void update(Category category);

    void delete(Category category);

    List<Category> getAll();

    Category getOne(long id);

    Category getByName(String name);
}
