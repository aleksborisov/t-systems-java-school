package com.marsarmy.service.interf;

import com.marsarmy.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getOne(Long id);

    Category getByName(String name);
}
