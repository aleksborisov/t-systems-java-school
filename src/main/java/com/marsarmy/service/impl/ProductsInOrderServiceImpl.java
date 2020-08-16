package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.ProductDao;
import com.marsarmy.dao.interf.ProductsInOrderDao;
import com.marsarmy.model.Order;
import com.marsarmy.model.Product;
import com.marsarmy.model.ProductsInOrder;
import com.marsarmy.service.interf.ProductsInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
public class ProductsInOrderServiceImpl implements ProductsInOrderService {

    private final ProductsInOrderDao productsInOrderDao;
    private final ProductDao productDao;

    @Autowired
    public ProductsInOrderServiceImpl(ProductsInOrderDao productsInOrderDao, ProductDao productDao) {
        this.productsInOrderDao = productsInOrderDao;
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void create(Order order, Map<Long, Integer> cart) {
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Product product = productDao.getOne(entry.getKey());

            ProductsInOrder productsInOrder = new ProductsInOrder();
            productsInOrder.setOrder(order);
            productsInOrder.setProduct(product);
            productsInOrder.setNumberOfProducts(entry.getValue());

            product.setInStock(product.getInStock() - entry.getValue());
            productDao.update(product);

            productsInOrderDao.create(productsInOrder);
        }
    }

    @Override
    public ProductsInOrder getOne(long id) {
        return productsInOrderDao.getOne(id);
    }
}
