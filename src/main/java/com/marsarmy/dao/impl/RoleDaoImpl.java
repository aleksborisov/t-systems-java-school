package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.RoleDao;
import com.marsarmy.model.Role;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<Role> getAll() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

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
