package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.OrderDao;
import com.marsarmy.model.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        return entityManager.createQuery("select o from Order o", Order.class).getResultList();
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
}
