package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.AddressDao;
import com.marsarmy.model.Address;
import com.marsarmy.service.interf.AddressService;
import com.marsarmy.service.interf.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service responsible for operations on addresses
 */
@Service
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;
    private final CustomerService customerService;

    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class);

    @Autowired
    public AddressServiceImpl(AddressDao addressDao, CustomerService customerService) {
        this.addressDao = addressDao;
        this.customerService = customerService;
    }

    /**
     * Creates a new address
     *
     * @param address Address entity to be created
     */
    @Override
    @Transactional
    public void create(Address address) {
        addressDao.create(address);
        LOGGER.info("User " + address.getCustomer().getEmail() + " create address " + address.toString());
    }

    /**
     * Updates transmitted address
     *
     * @param address Address entity to be updated
     */
    @Override
    @Transactional
    public void update(Address address) {
        addressDao.update(address);
        LOGGER.info("User " + address.getCustomer().getEmail() + " update address " + address.toString());
    }

    /**
     * Deletes transmitted address
     *
     * @param address Address entity to be deleted
     */
    @Override
    @Transactional
    public void delete(Address address) {
        addressDao.delete(address);
        LOGGER.info("User " + address.getCustomer().getEmail() + " delete address " + address.toString());
    }

    /**
     * Returns the list of all addresses
     *
     * @return {@link List} of {@link Address}
     */
    @Override
    public List<Address> getAll() {
        return addressDao.getAll();
    }

    /**
     * Returns the address by id
     *
     * @param id Id of address to get
     * @return {@link Address}
     */
    @Override
    public Address getOne(long id) {
        return addressDao.getOne(id);
    }

    /**
     * Returns the list of addresses of customer by its id
     *
     * @param customerId Id of customer to get addresses
     * @return {@link List} of {@link Address}
     */
    @Override
    public List<Address> getByCustomerId(long customerId) {
        return addressDao.getByCustomerId(customerId);
    }

    /**
     * Returns the list of addresses of customer by its email
     *
     * @param email Email of customer to get addresses
     * @return {@link List} of {@link Address}
     */
    @Override
    public List<Address> getByCustomerEmail(String email) {
        return addressDao.getByCustomerId(customerService.getOne(email).getId());
    }
}
