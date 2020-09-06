package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.OrderDao;
import com.marsarmy.model.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

/**
 * DAO class of {@link Order} entity
 */
@Component
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    /**
     * Creates a new row in the orders table
     *
     * @param order Order entity to add to the database
     */
    @Override
    public void create(Order order) {
        entityManager.persist(order);
    }

    /**
     * Updates a row in the orders table
     *
     * @param order Order entity to update in the database
     */
    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }

    /**
     * Returns the list of all orders in orders table
     *
     * @return {@link List} of {@link Order}
     */
    @Override
    public List<Order> getAll() {
        return entityManager.createQuery("select o from Order o order by o.dateOfSale desc", Order.class).getResultList();
    }

    /**
     * Returns the order by id
     *
     * @param id Id of order to get from the database
     * @return {@link Order}
     */
    @Override
    public Order getOne(long id) {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.id = :id",
                Order.class
        );
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    /**
     * Returns the list of orders of customer
     *
     * @param customerId Id of customer to get orders from the database
     * @return {@link List} of {@link Order}
     */
    @Override
    public List<Order> getByCustomer(long customerId) {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.customer.id = :customerId order by o.dateOfSale desc",
                Order.class
        );
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    /**
     * Returns the last order of customer
     *
     * @param email Email of customer to get the last order from the database
     * @return {@link Order}
     */
    @Override
    public Order getLast(String email) {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.customer.email = :email order by o.dateOfSale desc",
                Order.class
        );
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Returns income for the last week
     *
     * @return {@link BigDecimal}
     */
    @Override
    @SuppressWarnings("unchecked ")
    public BigDecimal getLastWeekIncome() {
        List<BigDecimal> result = entityManager.createNativeQuery("select sum(total) from orders" +
                " where payment_status_id <> 0 and date_of_sale >= now() - interval 7 day").getResultList();

        for (BigDecimal number : result) {
            if (number == null) {
                return new BigDecimal(0);
            }
        }

        return result.stream().findAny().orElse(new BigDecimal(0));
    }

    /**
     * Returns income for the last month
     *
     * @return {@link BigDecimal}
     */
    @Override
    @SuppressWarnings("unchecked ")
    public BigDecimal getLastMonthIncome() {
        List<BigDecimal> result = entityManager.createNativeQuery("select sum(total) from orders" +
                " where payment_status_id <> 0 and date_of_sale >= now() - interval 30 day").getResultList();

        for (BigDecimal number : result) {
            if (number == null) {
                return new BigDecimal(0);
            }
        }

        return result.stream().findAny().orElse(new BigDecimal(0));
    }
}
