package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.OrderDao;
import com.marsarmy.model.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void create(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }

    @Override
    public List<Order> getAll() {
        return entityManager.createQuery("select o from Order o order by o.dateOfSale desc", Order.class).getResultList();
    }

    @Override
    public Order getOne(long id) {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.id = :id",
                Order.class
        );
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<Order> getByCustomer(long customerId) {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.customer.id = :customerId order by o.dateOfSale desc",
                Order.class
        );
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

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
