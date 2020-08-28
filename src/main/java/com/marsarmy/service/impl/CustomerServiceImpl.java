package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.CustomerDao;
import com.marsarmy.statistics.CustomerStatistics;
import com.marsarmy.model.Customer;
import com.marsarmy.service.interf.CustomerService;
import com.marsarmy.service.interf.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Service responsible for operations on customers
 */
@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, BCryptPasswordEncoder bCryptPasswordEncoder,
                               RoleService roleService) {
        this.customerDao = customerDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

    /**
     * Creates a new customer
     *
     * @param customer Customer entity to be created
     */
    @Override
    @Transactional
    public void create(Customer customer) {
        customer.setRoles(Collections.singletonList(roleService.getOne("ROLE_USER")));
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerDao.create(customer);
        LOGGER.info("User " + customer.getEmail() + " was created");
    }

    /**
     * Updates transmitted customer
     *
     * @param customer Customer entity to be updated
     */
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
        LOGGER.info("User " + customer.getEmail() + " was updated");
    }

    /**
     * Returns the list of all customers
     *
     * @return {@link List} of {@link Customer}
     */
    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    /**
     * Returns the customer by email
     *
     * @param email Email of customer to get
     * @return {@link Customer}
     */
    @Override
    public Customer getOne(String email) {
        return customerDao.getOne(email);
    }

    /**
     * Returns current logged customer
     *
     * @return {@link Customer}
     */
    @Override
    public Customer getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customerDao.getOne(userDetails.getUsername());
    }

    /**
     * Returns the list of the top ten customers
     *
     * @return {@link List} of {@link CustomerStatistics}
     */
    @Override
    public List<CustomerStatistics> getTopTenCustomers() {
        return customerDao.getTopTenCustomers();
    }
}
