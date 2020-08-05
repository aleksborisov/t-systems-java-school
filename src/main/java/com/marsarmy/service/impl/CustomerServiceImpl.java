package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.CustomerDao;
import com.marsarmy.model.Customer;
import com.marsarmy.service.interf.CustomerService;
import com.marsarmy.service.interf.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, BCryptPasswordEncoder bCryptPasswordEncoder,
                               RoleService roleService) {
        this.customerDao = customerDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void create(Customer customer) {
        customer.setRoles(Collections.singletonList(roleService.getOne("ROLE_USER")));
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerDao.create(customer);
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        Customer oldCustomer = getCurrentUser();
        customer.setId(oldCustomer.getId());
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customer.setRoles(oldCustomer.getRoles());
        customer.setAddresses(oldCustomer.getAddresses());
        customer.setOrders(oldCustomer.getOrders());
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

    @Override
    public Customer getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customerDao.getOne(userDetails.getUsername());
    }
}
