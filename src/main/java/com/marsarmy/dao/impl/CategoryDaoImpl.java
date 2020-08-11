package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.CategoryDao;
import com.marsarmy.model.Category;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void create(Category category) {
        entityManager.persist(category);
    }

    @Override
    public void update(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void delete(Category category) {
        entityManager.createQuery("delete from Category where id = :id")
                .setParameter("id", category.getId())
                .executeUpdate();
    }

    @Override
    public List<Category> getAll() {
        return entityManager.createQuery("select c from Category c", Category.class).getResultList();
    }

    @Override
    public Category getOne(long id) {
        TypedQuery<Category> query = entityManager.createQuery(
                "select c from Category c where c.id = :id",
                Category.class
        );
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

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
