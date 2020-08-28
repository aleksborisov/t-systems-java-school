package com.marsarmy.dao.interf;

import com.marsarmy.model.ProductsInOrder;

/**
 * DAO interface of ProductsInOrder entity
 */
public interface ProductsInOrderDao {

    void create(ProductsInOrder productsInOrder);

    ProductsInOrder getOne(long id);
}
