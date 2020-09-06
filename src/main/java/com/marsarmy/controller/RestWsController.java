package com.marsarmy.controller;

import com.marsarmy.service.interf.ProductService;
import com.marsarmy.statistics.ProductStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestWsController {

    private final ProductService productService;

    @Autowired
    public RestWsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/stand")
    public List<ProductStatistics> getProductStatistics() {
        return productService.getTopTenProducts();
    }
}
