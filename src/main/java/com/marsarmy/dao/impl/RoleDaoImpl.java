package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.RoleDao;
import com.marsarmy.model.Role;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO class of {@link Role} entity
 */
@Component
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    /**
     * Returns the list of all roles in roles table
     *
     * @return {@link List} of {@link Role}
     */
    @Override
    public List<Role> getAll() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    /**
     * Returns the role by name
     *
     * @param name Name of role to get from the database
     * @return {@link Role}
     */
    @Override
    public Role getOne(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "select r from Role r where r.name = :name",
                Role.class
        );
        query.setParameter("name", name);
        return query.getResultList().stream().findAny().orElse(null);
    }
}
