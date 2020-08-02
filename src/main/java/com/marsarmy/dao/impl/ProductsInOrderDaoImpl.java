package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.ProductsInOrderDao;
import com.marsarmy.model.ProductsInOrder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class ProductsInOrderDaoImpl implements ProductsInOrderDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void create(ProductsInOrder productsInOrder) {
        entityManager.persist(productsInOrder);
    }

    @Override
    public ProductsInOrder getOne(long id) {
        TypedQuery<ProductsInOrder> query = entityManager.createQuery(
                "select i from ProductsInOrder i where i.id = :id",
                ProductsInOrder.class
        );
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }
}
