package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.ProductDao;
import com.marsarmy.model.Product;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void create(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    public List<Product> getAll() {
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Override
    public Product getOne(long upc) {
        TypedQuery<Product> query = entityManager.createQuery(
                "select p from Product p where p.upc = :upc",
                Product.class
        );
        query.setParameter("upc", upc);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<Product> filter(String category, String name, int minPrice,
                                int maxPrice, String brand, String color) {

        StringBuilder query = new StringBuilder(
                "select p from Product p where p.price >= :minPrice and p.price <= :maxPrice and p.deleted = false"
        );

        if (category != null) {
            query.append(" and p.category.name like '%")
                    .append(category)
                    .append("%'");
        }

        if (name != null && !name.isEmpty()) {
            query.append(" and p.name like '%")
                    .append(name)
                    .append("%'");
        }

        if (brand != null && !brand.isEmpty()) {
            query.append(" and p.brand like '%")
                    .append(brand)
                    .append("%'");
        }

        if (color != null && !color.isEmpty()) {
            query.append(" and p.color like '%")
                    .append(color)
                    .append("%'");
        }

        return entityManager.createQuery(query.toString(), Product.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }
}
