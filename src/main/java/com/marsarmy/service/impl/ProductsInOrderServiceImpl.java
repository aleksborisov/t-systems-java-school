package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.ProductDao;
import com.marsarmy.dao.interf.ProductsInOrderDao;
import com.marsarmy.model.Order;
import com.marsarmy.model.Product;
import com.marsarmy.model.ProductsInOrder;
import com.marsarmy.service.interf.ProductsInOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Service responsible for operations on productsInOrders
 */
@Service
@Transactional(readOnly = true)
public class ProductsInOrderServiceImpl implements ProductsInOrderService {

    private final ProductsInOrderDao productsInOrderDao;
    private final ProductDao productDao;

    private static final Logger LOGGER = Logger.getLogger(ProductsInOrderServiceImpl.class);

    @Autowired
    public ProductsInOrderServiceImpl(ProductsInOrderDao productsInOrderDao, ProductDao productDao) {
        this.productsInOrderDao = productsInOrderDao;
        this.productDao = productDao;
    }

    /**
     * Creates a new order
     *
     * @param order Order entity to be created
     * @param cart Map of product UPCs and its quantity in the cart
     */
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
            LOGGER.info("Product #" + product.getUpc() + " added to order #" + order.getId() + " in the amount of " +
                    productsInOrder.getNumberOfProducts() + " pc(s)");
        }
    }

    /**
     * Returns the productsInOrder by id
     *
     * @param id Id of productsInOrder to get
     * @return {@link ProductsInOrder}
     */
    @Override
    public ProductsInOrder getOne(long id) {
        return productsInOrderDao.getOne(id);
    }
}
