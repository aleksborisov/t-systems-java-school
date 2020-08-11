package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.OrderDao;
import com.marsarmy.model.Order;
import com.marsarmy.service.interf.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public void create(Order order) {
        orderDao.create(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order getOne(long id) {
        return orderDao.getOne(id);
    }

    @Override
    public List<Order> getByCustomer(long customerId) {
        return orderDao.getByCustomer(customerId);
    }

    @Override
    public BigDecimal getLastWeekIncome() {
        return orderDao.getLastWeekIncome();
    }

    @Override
    public BigDecimal getLastMonthIncome() {
        return orderDao.getLastMonthIncome();
    }
}
