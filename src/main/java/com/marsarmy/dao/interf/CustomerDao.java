package com.marsarmy.dao.interf;

import com.marsarmy.statistics.CustomerStatistics;
import com.marsarmy.model.Customer;

import java.util.List;

/**
 * DAO interface of Customer entity
 */
public interface CustomerDao {

    void create(Customer customer);

    void update(Customer customer);

    List<Customer> getAll();

    Customer getOne(String email);

    List<CustomerStatistics> getTopTenCustomers();
}
