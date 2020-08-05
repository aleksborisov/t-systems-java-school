package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.AddressDao;
import com.marsarmy.model.Address;
import com.marsarmy.service.interf.AddressService;
import com.marsarmy.service.interf.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;
    private final CustomerService customerService;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao, CustomerService customerService) {
        this.addressDao = addressDao;
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public void create(Address address) {
        addressDao.create(address);
    }

    @Override
    @Transactional
    public void update(Address address) {
        addressDao.update(address);
    }

    @Override
    @Transactional
    public void delete(Address address) {
        addressDao.delete(address);
    }

    @Override
    public List<Address> getAll() {
        return addressDao.getAll();
    }

    @Override
    public Address getOne(long id) {
        return addressDao.getOne(id);
    }

    @Override
    public List<Address> getByCustomerId(long customerId) {
        return addressDao.getByCustomerId(customerId);
    }

    @Override
    public List<Address> getByCustomerEmail(String email) {
        return addressDao.getByCustomerId(customerService.getOne(email).getId());
    }
}
