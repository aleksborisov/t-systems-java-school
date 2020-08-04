package com.marsarmy.dao.interf;

import com.marsarmy.model.ProductsInOrder;

public interface ProductsInOrderDao {

    void create(ProductsInOrder productsInOrder);

    ProductsInOrder getOne(long id);
}
