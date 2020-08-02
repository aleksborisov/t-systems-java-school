package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.AddressDao;
import com.marsarmy.model.Address;
import com.marsarmy.service.interf.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
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
    public List<Address> getAll() {
        return addressDao.getAll();
    }

    @Override
    public Address getOne(long id) {
        return addressDao.getOne(id);
    }

    @Override
    public Address getByCustomerId(long customerId) {
        return addressDao.getByCustomerId(customerId);
    }
}
