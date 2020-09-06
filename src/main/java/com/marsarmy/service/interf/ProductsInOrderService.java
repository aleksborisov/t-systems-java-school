package com.marsarmy.service.interf;

import com.marsarmy.model.Order;
import com.marsarmy.model.ProductsInOrder;

import java.util.Map;

/**
 * Service interface responsible for operations on productsInOrders
 */
public interface ProductsInOrderService {

    void create(Order order, Map<Long, Integer> cart);

    ProductsInOrder getOne(long id);
}
