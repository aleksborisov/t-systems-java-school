package com.marsarmy.dao.interf;

import com.marsarmy.model.Address;

import java.util.List;

public interface AddressDao {

    void create(Address address);

    void update(Address address);

    void delete(Address address);

    List<Address> getAll();

    Address getOne(long id);

    List<Address> getByCustomerId(long customerId);
}
