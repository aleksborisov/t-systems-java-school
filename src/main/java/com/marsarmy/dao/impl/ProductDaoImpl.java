package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.ProductDao;
import com.marsarmy.model.Category;
import com.marsarmy.statistics.ProductStatistics;
import com.marsarmy.model.Product;
import com.marsarmy.model.enumeration.PaymentStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class of {@link Product} entity
 */
@Component
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    /**
     * Creates a new row in the products table
     *
     * @param product Product entity to add to the database
     */
    @Override
    public void create(Product product) {
        entityManager.persist(product);
    }

    /**
     * Updates a row in the products table
     *
     * @param product Product entity to update in the database
     */
    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    /**
     * Returns the list of all products in products table
     *
     * @return {@link List} of {@link Product}
     */
    @Override
    public List<Product> getAll() {
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }

    /**
     * Returns the product by upc
     *
     * @param upc UPC of product to get from the database
     * @return {@link Product}
     */
    @Override
    public Product getOne(long upc) {
        TypedQuery<Product> query = entityManager.createQuery(
                "select p from Product p where p.upc = :upc",
                Product.class
        );
        query.setParameter("upc", upc);
        return query.getResultList().stream().findAny().orElse(null);
    }

    /**
     * Returns the list of products of category
     *
     * @param category Category name to get products from the database
     * @return {@link List} of {@link Product}
     */
    @Override
    public List<Product> getByCategory(String category) {
        return entityManager.createQuery("select p from Product p where p.category.name = :category", Product.class)
                .setParameter("category", category)
                .getResultList();
    }

    /**
     * Filters products by their fields
     *
     * @param category Category name
     * @param name     Product name or its part
     * @param minPrice Minimum price include minPrice
     * @param maxPrice Maximum price include maxPrice
     * @param brand    Product brand
     * @param color    Product color
     * @return {@link List} of {@link Product}
     */
    @Override
    public List<Product> filter(String category, String name, int minPrice,
                                int maxPrice, String brand, String color) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        Predicate predicate = criteriaBuilder.ge(root.get("price"), minPrice);
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.le(root.get("price"), maxPrice));
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("deleted"), false));

        if (category != null) {
            Path<Category> categoryPath = root.get("category");
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(categoryPath.get("name"), "%" + category + "%"));
        }

        if (name != null && !name.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        if (brand != null && !brand.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("brand"), "%" + brand + "%"));
        }

        if (color != null && !color.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("color"), "%" + color + "%"));
        }

        return entityManager.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
    }

    /**
     * Returns the list of the top ten products
     *
     * @return {@link List} of {@link ProductStatistics}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ProductStatistics> getTopTenProducts() {
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

        List<ProductStatistics> productsStatistics = new ArrayList<>();

        for (Object[] objects : result) {
            ProductStatistics productStatistics = new ProductStatistics();
            productStatistics.setUpc((Long) objects[0]);
            productStatistics.setName((String) objects[1]);
            productStatistics.setColor((String) objects[2]);
            productStatistics.setBrand((String) objects[3]);
            productStatistics.setCategory((String) objects[4]);
            productStatistics.setPrice((Integer) objects[5]);
            productStatistics.setQuantitySold((Long) objects[6]);
            productsStatistics.add(productStatistics);
        }

        return productsStatistics;
    }
}
