package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.OrderDao;
import com.marsarmy.model.Order;
import com.marsarmy.service.interf.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service responsible for operations on orders
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * Creates a new order
     *
     * @param order Order entity to be created
     */
    @Override
    @Transactional
    public void create(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order can't be null");
        }

        orderDao.create(order);
        LOGGER.info("Order #" + order.getId() + " for customer " + order.getCustomer().getEmail() + " was created");
    }

    /**
     * Updates transmitted order
     *
     * @param order Order entity to be updated
     */
    @Override
    @Transactional
    public void update(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order can't be null");
        }

        orderDao.update(order);
        LOGGER.info("Order #" + order.getId() + " for customer " + order.getCustomer().getEmail() + " was updated");
    }

    /**
     * Returns the list of all orders
     *
     * @return {@link List} of {@link Order}
     */
    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    /**
     * Returns the order by id
     *
     * @param id Id of order to get
     * @return {@link Order}
     */
    @Override
    public Order getOne(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Order id can't be less than 1");
        }

        return orderDao.getOne(id);
    }

    /**
     * Returns the list of orders of customer by its id
     *
     * @param customerId Id of customer to get orders
     * @return {@link List} of {@link Order}
     */
    @Override
    public List<Order> getByCustomer(long customerId) {
        if (customerId < 1) {
            throw new IllegalArgumentException("Customer id can't be less than 1");
        }

        return orderDao.getByCustomer(customerId);
    }

    /**
     * Returns the last order of customer
     *
     * @param email Email of customer to get the last order
     * @return {@link Order}
     */
    @Override
    public Order getLast(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Customer email can't be null or empty");
        }

        return orderDao.getLast(email);
    }

    /**
     * Returns income for the last week
     *
     * @return {@link BigDecimal}
     */
    @Override
    public BigDecimal getLastWeekIncome() {
        return orderDao.getLastWeekIncome();
    }

    /**
     * Returns income for the last month
     *
     * @return {@link BigDecimal}
     */
    @Override
    public BigDecimal getLastMonthIncome() {
        return orderDao.getLastMonthIncome();
    }
}
