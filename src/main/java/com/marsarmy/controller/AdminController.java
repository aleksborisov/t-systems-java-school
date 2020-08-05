package com.marsarmy.controller;

import com.marsarmy.converter.CategoryConverter;
import com.marsarmy.converter.ProductConverter;
import com.marsarmy.dto.ProductDto;
import com.marsarmy.model.Product;
import com.marsarmy.service.interf.CategoryService;
import com.marsarmy.service.interf.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;
    private final CategoryConverter categoryConverter;

    @Autowired
    public AdminController(ProductService productService, CategoryService categoryService,
                           ProductConverter productConverter, CategoryConverter categoryConverter) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productConverter = productConverter;
        this.categoryConverter = categoryConverter;
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin/admin";
    }

    @GetMapping("/edit_product")
    public String getEditProduct(Model model, @RequestParam Long upc) {
        model.addAttribute("productDto", productConverter.convertToDto(productService.getOne(upc)));
        model.addAttribute("categoriesDto", categoryConverter.convertToListOfDto(categoryService.getAll()));
        return "admin/edit_product";
    }

    @PostMapping("/edit_product")
    public String editProduct(@ModelAttribute("productDto") ProductDto productDto) {
        productService.update(productConverter.convertToEntity(productDto));
        return "redirect:/catalog";
    }

    @GetMapping("/delete_product")
    public String getDeleteProduct(Model model, @RequestParam Long upc) {
        Product product = productService.getOne(upc);
        product.setDeleted(true);
        model.addAttribute("productDto", productConverter.convertToDto(product));
        return "admin/delete_product";
    }

    @PostMapping("/delete_product")
    public String deleteProduct(@ModelAttribute("productDto") ProductDto productDto) {
        productService.update(productConverter.convertToEntity(productDto));
        return "redirect:/catalog";
    }
}
