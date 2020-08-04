package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.CustomerDao;
import com.marsarmy.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerDao customerDao;

    @Autowired
    public UserDetailsServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerDao.getOne(email);

        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found");
        }

        return customer;
    }
}
