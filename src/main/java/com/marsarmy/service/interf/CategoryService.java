package com.marsarmy.service.interf;

import com.marsarmy.model.Category;

import java.util.List;

/**
 * Service interface responsible for operations on categories
 */
public interface CategoryService {

    void create(Category category);

    void update(Category category);

    void delete(Category category);

    List<Category> getAll();

    Category getOne(Long id);

    Category getByName(String name);
}
