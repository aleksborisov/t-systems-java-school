package com.marsarmy.converter;

import com.marsarmy.dto.ProductsInOrderDto;
import com.marsarmy.model.ProductsInOrder;
import com.marsarmy.service.interf.OrderService;
import com.marsarmy.service.interf.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsInOrderConverter {

    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public ProductsInOrderConverter(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    public ProductsInOrderDto convertToDto(ProductsInOrder productsInOrder) {
        ProductsInOrderDto productsInOrderDto = new ProductsInOrderDto();

        productsInOrderDto.setId(productsInOrder.getId());
        productsInOrderDto.setProductDto(productsInOrder.getProduct().getUpc());
        productsInOrderDto.setOrderDto(productsInOrder.getOrder().getId());
        productsInOrderDto.setNumberOfProducts(productsInOrder.getNumberOfProducts());

        return productsInOrderDto;
    }

    public ProductsInOrder convertToEntity(ProductsInOrderDto productsInOrderDto) {
        ProductsInOrder productsInOrder = new ProductsInOrder();

        productsInOrder.setId(productsInOrder.getId());
        productsInOrder.setProduct(productService.getOne(productsInOrderDto.getProductDto()));
        productsInOrder.setOrder(orderService.getOne(productsInOrderDto.getOrderDto()));
        productsInOrder.setNumberOfProducts(productsInOrder.getNumberOfProducts());

        return productsInOrder;
    }

    public List<ProductsInOrderDto> convertToListOfDto(List<ProductsInOrder> productsInOrders) {
        List<ProductsInOrderDto> productsInOrdersDto = new ArrayList<>();
        for (ProductsInOrder productsInOrder : productsInOrders) {
            productsInOrdersDto.add(convertToDto(productsInOrder));
        }
        return productsInOrdersDto;
    }

    public List<ProductsInOrder> convertToListOfEntity(List<ProductsInOrderDto> productsInOrdersDto) {
        List<ProductsInOrder> productsInOrders = new ArrayList<>();
        for (ProductsInOrderDto productsInOrderDto : productsInOrdersDto) {
            productsInOrders.add(convertToEntity(productsInOrderDto));
        }
        return productsInOrders;
    }
}
