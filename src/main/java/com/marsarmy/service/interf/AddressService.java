package com.marsarmy.service.interf;

import com.marsarmy.model.Address;

import java.util.List;

public interface AddressService {

    void create(Address address);

    void update(Address address);

    List<Address> getAll();

    Address getOne(long id);

    Address getByCustomerId(long customerId);
}
