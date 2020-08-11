package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.ProductDao;
import com.marsarmy.dto.ProductStatisticsDto;
import com.marsarmy.model.Product;
import com.marsarmy.model.enumeration.PaymentStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductStatisticsDto> getTopTenProducts() {
        List<Object[]> result = entityManager.createQuery("select p.upc, p.name, p.color, p.brand, p.category.name, p.price," +
                " sum (po.numberOfProducts) as quantitySold" +
                " from Product p" +
                " left join ProductsInOrder po on p.upc = po.product.upc" +
                " left join Order o on o.id = po.order.id" +
                " where o.paymentStatus = :paymentStatus" +
                " and p.deleted = false" +
                " group by p.upc" +
                " order by quantitySold desc")
                .setParameter("paymentStatus", PaymentStatus.PAID)
                .setMaxResults(10)
                .getResultList();

        if (result == null) {
            return new ArrayList<>();
        }

        List<ProductStatisticsDto> productsStatisticsDto = new ArrayList<>();

        for (Object[] objects : result) {
            ProductStatisticsDto productStatisticsDto = new ProductStatisticsDto();
            productStatisticsDto.setUpc((Long) objects[0]);
            productStatisticsDto.setName((String) objects[1]);
            productStatisticsDto.setColor((String) objects[2]);
            productStatisticsDto.setBrand((String) objects[3]);
            productStatisticsDto.setCategory((String) objects[4]);
            productStatisticsDto.setPrice((Integer) objects[5]);
            productStatisticsDto.setQuantitySold((Long) objects[6]);
            productsStatisticsDto.add(productStatisticsDto);
        }

        return productsStatisticsDto;
    }
}
