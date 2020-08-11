package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.AddressDao;
import com.marsarmy.model.Address;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void create(Address address) {
        entityManager.persist(address);
    }

    @Override
    public void update(Address address) {
        entityManager.merge(address);
    }

    @Override
    public void delete(Address address) {
        entityManager.createQuery("delete from Address where id = :id")
                .setParameter("id", address.getId())
                .executeUpdate();
    }

    @Override
    public List<Address> getAll() {
        return entityManager.createQuery("select a from Address a", Address.class).getResultList();
    }

    @Override
    public Address getOne(long id) {
        TypedQuery<Address> query = entityManager.createQuery(
                "select a from Address a where a.id = :id",
                Address.class
        );
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<Address> getByCustomerId(long customerId) {
        TypedQuery<Address> query = entityManager.createQuery(
                "select a from Address a where a.customer.id = :customerId",
                Address.class
        );
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
}
