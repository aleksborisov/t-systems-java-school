package com.marsarmy.service.interf;

import com.marsarmy.model.Order;
import com.marsarmy.model.ProductsInOrder;

import java.util.Map;

public interface ProductsInOrderService {

    void create(Order order, Map<Long, Integer> cart);

    ProductsInOrder getOne(long id);
}
