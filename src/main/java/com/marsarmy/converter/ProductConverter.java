package com.marsarmy.converter;

import com.marsarmy.dto.ProductDto;
import com.marsarmy.model.Product;
import com.marsarmy.service.interf.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    private final CategoryService categoryService;

    @Autowired
    public ProductConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setUpc(product.getUpc());
        productDto.setCategoryDto(product.getCategory().getName());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setBrand(product.getBrand());
        productDto.setColor(product.getColor());
        productDto.setWeight(product.getWeight());
        productDto.setHeight(product.getHeight());
        productDto.setWidth(product.getWidth());
        productDto.setDepth(product.getDepth());
        productDto.setInStock(product.getInStock());
        productDto.setDeleted(product.isDeleted());

        return productDto;
    }

    public Product convertToEntity(ProductDto productDto) {
        Product product = new Product();

        product.setUpc(productDto.getUpc());
        product.setCategory(categoryService.getByName(productDto.getCategoryDto()));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setBrand(productDto.getBrand());
        product.setColor(productDto.getColor());
        product.setWeight(productDto.getWeight());
        product.setHeight(productDto.getHeight());
        product.setWidth(productDto.getWidth());
        product.setDepth(productDto.getDepth());
        product.setInStock(productDto.getInStock());
        product.setDeleted(productDto.getDeleted());

        return product;
    }

    public List<ProductDto> convertToListOfDto(List<Product> products) {
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(convertToDto(product));
        }
        return productsDto;
    }

    public List<Product> convertToListOfEntity(List<ProductDto> productsDto) {
        List<Product> products = new ArrayList<>();
        for (ProductDto productDto : productsDto) {
            products.add(convertToEntity(productDto));
        }
        return products;
    }
}
