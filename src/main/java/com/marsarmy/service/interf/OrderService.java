package com.marsarmy.service.interf;

import com.marsarmy.model.Order;

import java.util.List;

public interface OrderService {

    void create(Order order);

    void update(Order order);

    List<Order> getAll();

    Order getOne(long id);

    List<Order> getByCustomer(long customerId);
}
