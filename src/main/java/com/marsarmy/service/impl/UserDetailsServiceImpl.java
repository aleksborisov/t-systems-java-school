package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.CustomerDao;
import com.marsarmy.model.Customer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service required for Spring Security to work
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerDao customerDao;

    private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    public UserDetailsServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * Returns UserDetails for processing by Spring Security
     *
     * @param email Email of customer to process
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException If there is no customer with provided email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerDao.getOne(email);

        if (customer == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }

        LOGGER.info("User " + customer.getEmail() + " is logged in");
        return customer;
    }
}
