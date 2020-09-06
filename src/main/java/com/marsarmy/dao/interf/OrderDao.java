package com.marsarmy.dao.interf;

import com.marsarmy.model.Order;

import java.math.BigDecimal;
import java.util.List;

/**
 * DAO interface of Order entity
 */
public interface OrderDao {

    void create(Order order);

    void update(Order order);

    List<Order> getAll();

    Order getOne(long id);

    List<Order> getByCustomer(long customerId);

    Order getLast(String email);

    BigDecimal getLastWeekIncome();

    BigDecimal getLastMonthIncome();
}
