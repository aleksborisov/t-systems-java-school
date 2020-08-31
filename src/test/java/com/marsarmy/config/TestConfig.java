package com.marsarmy.config;

import com.marsarmy.converter.ProductConverter;
import com.marsarmy.dao.interf.*;
import com.marsarmy.service.impl.*;
import com.marsarmy.service.interf.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public AddressService addressService() {
        return new AddressServiceImpl(addressDao(), customerService());
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl(categoryDao(), productDao());
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl(customerDao(), bCryptPasswordEncoder(), roleService());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(orderDao());
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl(productDao(), productConverter());
    }

    @Bean
    public ProductsInOrderService productsInOrderService() {
        return new ProductsInOrderServiceImpl(productsInOrderDao(), productDao());
    }

    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl(roleDao());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(customerDao());
    }

    @Bean
    public AddressDao addressDao() {
        return mock(AddressDao.class);
    }

    @Bean
    public CategoryDao categoryDao() {
        return mock(CategoryDao.class);
    }

    @Bean
    public CustomerDao customerDao() {
        return mock(CustomerDao.class);
    }

    @Bean
    public OrderDao orderDao() {
        return mock(OrderDao.class);
    }

    @Bean
    public ProductDao productDao() {
        return mock(ProductDao.class);
    }

    @Bean
    public ProductsInOrderDao productsInOrderDao() {
        return mock(ProductsInOrderDao.class);
    }

    @Bean
    public RoleDao roleDao() {
        return mock(RoleDao.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return mock(BCryptPasswordEncoder.class);
    }

    @Bean
    public ProductConverter productConverter() {
        return mock(ProductConverter.class);
    }
}
