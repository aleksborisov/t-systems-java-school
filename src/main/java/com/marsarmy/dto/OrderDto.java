package com.marsarmy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Long id;
    private String customerDto;
    private String paymentMethod;
    private String deliveryMethod;
    private String paymentStatus;
    private String orderStatus;
    private String address;
    private Integer total;
    private LocalDate dateOfSale;

    private List<ProductsInOrderDto> productsInOrdersDto;
}
