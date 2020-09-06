package com.marsarmy.service;

import com.marsarmy.config.TestConfig;
import com.marsarmy.dao.interf.CustomerDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomerDao customerDao;

    private static final String email = "any@gmail.com";

    @Test
    public void emailShouldNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> userDetailsService.loadUserByUsername(null));
    }

    @Test
    public void emailShouldNotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> userDetailsService.loadUserByUsername(""));
    }

    @Test
    public void customerShouldExist() {
        when(customerDao.getOne(email)).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(email));
    }
}
