package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.CategoryDao;
import com.marsarmy.model.Category;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO class of {@link Category} entity
 */
@Component
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    /**
     * Creates a new row in the categories table
     *
     * @param category Category entity to add to the database
     */
    @Override
    public void create(Category category) {
        entityManager.persist(category);
    }

    /**
     * Updates a row in the categories table
     *
     * @param category Category entity to update in the database
     */
    @Override
    public void update(Category category) {
        entityManager.merge(category);
    }

    /**
     * Deletes a row in the categories table
     *
     * @param category Category entity to delete from the database
     */
    @Override
    public void delete(Category category) {
        entityManager.createQuery("delete from Category where id = :id")
                .setParameter("id", category.getId())
                .executeUpdate();
    }

    /**
     * Returns the list of all categories in the categories table
     *
     * @return {@link List} of {@link Category}
     */
    @Override
    public List<Category> getAll() {
        return entityManager.createQuery("select c from Category c", Category.class).getResultList();
    }

    /**
     * Returns the category by id
     *
     * @param id Id of category to get from the database
     * @return {@link Category}
     */
    @Override
    public Category getOne(long id) {
        TypedQuery<Category> query = entityManager.createQuery(
                "select c from Category c where c.id = :id",
                Category.class
        );
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    /**
     * Returns the category by name
     *
     * @param name Name of category to get from the database
     * @return {@link Category}
     */
    @Override
    public Category getByName(String name) {
        TypedQuery<Category> query = entityManager.createQuery(
                "select c from Category c where c.name = :name",
                Category.class
        );
        query.setParameter("name", name);
        return query.getResultList().stream().findAny().orElse(null);
    }
}
