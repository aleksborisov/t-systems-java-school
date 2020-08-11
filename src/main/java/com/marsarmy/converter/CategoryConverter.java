package com.marsarmy.converter;

import com.marsarmy.dto.CategoryDto;
import com.marsarmy.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    public CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    public Category convertToEntity(CategoryDto categoryDto) {
        Category category = new Category();

        if (categoryDto.getId() != null) {
            category.setId(categoryDto.getId());
        }

        category.setName(categoryDto.getName());

        return category;
    }

    public List<CategoryDto> convertToListOfDto(List<Category> categories) {
        List<CategoryDto> categoriesDto = new ArrayList<>();
        for (Category category : categories) {
            categoriesDto.add(convertToDto(category));
        }
        return categoriesDto;
    }

    public List<Category> convertToListOfEntity(List<CategoryDto> categoriesDto) {
        List<Category> categories = new ArrayList<>();
        for (CategoryDto categoryDto : categoriesDto) {
            categories.add(convertToEntity(categoryDto));
        }
        return categories;
    }
}
