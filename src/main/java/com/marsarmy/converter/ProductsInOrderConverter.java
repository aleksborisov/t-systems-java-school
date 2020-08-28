package com.marsarmy.converter;

import com.marsarmy.dto.ProductsInOrderDto;
import com.marsarmy.model.ProductsInOrder;
import com.marsarmy.service.interf.OrderService;
import com.marsarmy.service.interf.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter for ProductsInOrder entity and ProductsInOrderDto
 */
@Component
public class ProductsInOrderConverter {

    private final OrderService orderService;
    private final ProductService productService;
    private final ProductConverter productConverter;

    @Autowired
    public ProductsInOrderConverter(OrderService orderService, ProductService productService,
                                    ProductConverter productConverter) {
        this.orderService = orderService;
        this.productService = productService;
        this.productConverter = productConverter;
    }

    /**
     * Convert ProductsInOrder entity to ProductsInOrderDto
     *
     * @param productsInOrder ProductsInOrder entity to convert
     * @return {@link ProductsInOrderDto}
     */
    public ProductsInOrderDto convertToDto(ProductsInOrder productsInOrder) {
        ProductsInOrderDto productsInOrderDto = new ProductsInOrderDto();

        productsInOrderDto.setId(productsInOrder.getId());
        productsInOrderDto.setProductDto(productConverter.convertToDto(productsInOrder.getProduct()));
        productsInOrderDto.setOrderDto(productsInOrder.getOrder().getId());
        productsInOrderDto.setNumberOfProducts(productsInOrder.getNumberOfProducts());

        return productsInOrderDto;
    }

    /**
     * Convert ProductsInOrderDto to ProductsInOrder entity
     *
     * @param productsInOrderDto ProductsInOrderDto to convert
     * @return {@link ProductsInOrder}
     */
    public ProductsInOrder convertToEntity(ProductsInOrderDto productsInOrderDto) {
        ProductsInOrder productsInOrder = new ProductsInOrder();

        productsInOrder.setId(productsInOrder.getId());
        productsInOrder.setProduct(productService.getOne(productsInOrderDto.getProductDto().getUpc()));
        productsInOrder.setOrder(orderService.getOne(productsInOrderDto.getId()));
        productsInOrder.setNumberOfProducts(productsInOrder.getNumberOfProducts());

        return productsInOrder;
    }

    /**
     * Convert List of ProductsInOrder entities to List of ProductsInOrderDto
     *
     * @param productsInOrders List of ProductsInOrder entities to convert
     * @return {@link List} of {@link ProductsInOrderDto}
     */
    public List<ProductsInOrderDto> convertToListOfDto(List<ProductsInOrder> productsInOrders) {
        List<ProductsInOrderDto> productsInOrdersDto = new ArrayList<>();
        for (ProductsInOrder productsInOrder : productsInOrders) {
            productsInOrdersDto.add(convertToDto(productsInOrder));
        }
        return productsInOrdersDto;
    }

    /**
     * Convert List of ProductsInOrderDto to List of ProductsInOrder entities
     *
     * @param productsInOrdersDto List of ProductsInOrderDto to convert
     * @return {@link List} of {@link ProductsInOrder}
     */
    public List<ProductsInOrder> convertToListOfEntity(List<ProductsInOrderDto> productsInOrdersDto) {
        List<ProductsInOrder> productsInOrders = new ArrayList<>();
        for (ProductsInOrderDto productsInOrderDto : productsInOrdersDto) {
            productsInOrders.add(convertToEntity(productsInOrderDto));
        }
        return productsInOrders;
    }
}
