package com.marsarmy.controller;

import com.marsarmy.converter.CategoryConverter;
import com.marsarmy.converter.OrderConverter;
import com.marsarmy.converter.ProductConverter;
import com.marsarmy.dto.CategoryDto;
import com.marsarmy.dto.OrderDto;
import com.marsarmy.dto.ProductDto;
import com.marsarmy.model.Product;
import com.marsarmy.service.interf.CategoryService;
import com.marsarmy.service.interf.CustomerService;
import com.marsarmy.service.interf.OrderService;
import com.marsarmy.service.interf.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductConverter productConverter;
    private final CategoryConverter categoryConverter;
    private final OrderConverter orderConverter;

    @Autowired
    public AdminController(ProductService productService, CategoryService categoryService,
                           OrderService orderService, CustomerService customerService,
                           ProductConverter productConverter, CategoryConverter categoryConverter,
                           OrderConverter orderConverter) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.productConverter = productConverter;
        this.categoryConverter = categoryConverter;
        this.orderConverter = orderConverter;
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin/admin";
    }

    @GetMapping("/create_product")
    public String getCreateProduct(Model model) {
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("categoriesDto", categoryConverter.convertToListOfDto(categoryService.getAll()));
        return "admin/create_product";
    }

    @PostMapping("/create_product")
    public String createProduct(@ModelAttribute("productDto") ProductDto productDto) {
        productService.create(productConverter.convertToEntity(productDto));
        return "redirect:/admin/admin";
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

    @GetMapping("/orders")
    public String getOrders(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        model.addAttribute("formatter", formatter);
        model.addAttribute("ordersDto", orderConverter.convertToListOfDto(orderService.getAll()));
        return "admin/orders";
    }

    @GetMapping("/change_order_status")
    public String getChangeOrderStatus(Model model, @RequestParam Long id) {
        model.addAttribute("orderDto", orderConverter.convertToDto(orderService.getOne(id)));
        return "admin/change_order_status";
    }

    @PostMapping("/change_order_status")
    public String changeOrderStatus(@ModelAttribute OrderDto orderDto) {
        orderDto.setDateOfSale(orderService.getOne(orderDto.getId()).getDateOfSale());
        orderService.update(orderConverter.convertToEntity(orderDto));
        return "redirect:/admin/orders";
    }

    @GetMapping("/categories")
    public String getCategories(Model model) {
        model.addAttribute("categoriesDto", categoryConverter.convertToListOfDto(categoryService.getAll()));
        return "admin/categories";
    }

    @GetMapping("/create_category")
    public String getCreateCategory(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "admin/create_category";
    }

    @PostMapping("/create_category")
    public String createCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.create(categoryConverter.convertToEntity(categoryDto));
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit_category")
    public String getEditCategory(Model model, @RequestParam Long id) {
        model.addAttribute("categoryDto", categoryConverter.convertToDto(categoryService.getOne(id)));
        return "admin/edit_category";
    }

    @PostMapping("/edit_category")
    public String editCategory(@ModelAttribute CategoryDto categoryDto) {
        categoryService.update(categoryConverter.convertToEntity(categoryDto));
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete_category")
    public String getDeleteCategory(Model model, @RequestParam Long id) {
        model.addAttribute("categoryDto", categoryConverter.convertToDto(categoryService.getOne(id)));
        return "admin/delete_category";
    }

    @PostMapping("/delete_category")
    public String deleteCategory(@ModelAttribute CategoryDto categoryDto) {
        categoryService.delete(categoryConverter.convertToEntity(categoryDto));
        return "redirect:/admin/categories";
    }

    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        model.addAttribute("productsStatisticsDto", productService.getTopTenProducts());
        model.addAttribute("customersStatisticsDto", customerService.getTopTenCustomers());
        model.addAttribute("lastWeekIncome", orderService.getLastWeekIncome());
        model.addAttribute("lastMonthIncome", orderService.getLastMonthIncome());
        return "admin/statistics";
    }
}
