package com.marsarmy.service.interf;

import com.marsarmy.statistics.CustomerStatistics;
import com.marsarmy.model.Customer;

import java.util.List;

/**
 * Service interface responsible for operations on customers
 */
public interface CustomerService {

    void create(Customer customer);

    void update(Customer customer);

    List<Customer> getAll();

    Customer getOne(String email);

    Customer getCurrentUser();

    List<CustomerStatistics> getTopTenCustomers();
}
