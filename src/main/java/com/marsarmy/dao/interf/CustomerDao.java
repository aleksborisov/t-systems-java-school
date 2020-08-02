package com.marsarmy.dao.interf;

import com.marsarmy.model.Customer;

import java.util.List;

public interface CustomerDao {
    void create(Customer customer);

    void update(Customer customer);

    List<Customer> getAll();

    Customer getOne(String email);
}
