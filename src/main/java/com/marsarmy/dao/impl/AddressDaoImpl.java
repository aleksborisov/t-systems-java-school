package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.AddressDao;
import com.marsarmy.model.Address;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO class of {@link Address} entity
 */
@Component
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    /**
     * Creates a new row in the addresses table
     *
     * @param address Address entity to add to the database
     */
    @Override
    public void create(Address address) {
        entityManager.persist(address);
    }

    /**
     * Updates a row in the addresses table
     *
     * @param address Address entity to update in the database
     */
    @Override
    public void update(Address address) {
        entityManager.merge(address);
    }

    /**
     * Deletes a row in the addresses table
     *
     * @param address Address entity to delete from the database
     */
    @Override
    public void delete(Address address) {
        entityManager.createQuery("delete from Address where id = :id")
                .setParameter("id", address.getId())
                .executeUpdate();
    }

    /**
     * Returns the list of all addresses in the addresses table
     *
     * @return {@link List} of {@link Address}
     */
    @Override
    public List<Address> getAll() {
        return entityManager.createQuery("select a from Address a", Address.class).getResultList();
    }

    /**
     * Returns the address by id
     *
     * @param id Id of address to get from the database
     * @return {@link Address}
     */
    @Override
    public Address getOne(long id) {
        TypedQuery<Address> query = entityManager.createQuery(
                "select a from Address a where a.id = :id",
                Address.class
        );
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    /**
     * Returns the list of addresses of customer
     *
     * @param customerId Id of customer to get addresses from the database
     * @return {@link List} of {@link Address}
     */
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
