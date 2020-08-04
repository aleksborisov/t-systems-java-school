package com.marsarmy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductsInOrderDto {

    private Long id;
    private Long productDto;
    private Long orderDto;
    private Integer numberOfProducts;
}