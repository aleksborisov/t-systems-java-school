package com.marsarmy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Long id;
    private String customerDto;
    private Long addressDto;
    private String paymentMethod;
    private String deliveryMethod;
    private String paymentStatus;
    private String orderStatus;

    private List<ProductsInOrderDto> productsInOrdersDto;
}
