package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.CustomerDao;
import com.marsarmy.model.Customer;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void create(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public List<Customer> getAll() {
        return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer getOne(String email) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "select c from Customer c where c.email = :email",
                Customer.class
        );
        query.setParameter("email", email);
        return query.getResultList().stream().findAny().orElse(null);
    }
}
