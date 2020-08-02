package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.CustomerDao;
import com.marsarmy.model.Customer;
import com.marsarmy.service.interf.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    @Transactional
    public void create(Customer customer) {
        customerDao.create(customer);
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public Customer getOne(String email) {
        return customerDao.getOne(email);
    }
}
