package com.marsarmy.dto;

import com.marsarmy.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO class of {@link Category} entity
 */
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String name;
}
