package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.ProductsInOrderDao;
import com.marsarmy.model.ProductsInOrder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * DAO class of {@link ProductsInOrder} entity
 */
@Component
public class ProductsInOrderDaoImpl implements ProductsInOrderDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    /**
     * Creates a new row in the products_in_orders table
     *
     * @param productsInOrder ProductsInOrder entity to add to the database
     */
    @Override
    public void create(ProductsInOrder productsInOrder) {
        entityManager.persist(productsInOrder);
    }

    /**
     * Returns the productsInOrder by id
     *
     * @param id Id of productsInOrder to get from the database
     * @return {@link ProductsInOrder}
     */
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
