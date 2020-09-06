package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.CustomerDao;
import com.marsarmy.statistics.CustomerStatistics;
import com.marsarmy.model.Customer;
import com.marsarmy.model.enumeration.PaymentStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class of {@link Customer} entity
 */
@Component
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    /**
     * Creates a new row in the customers table
     *
     * @param customer Customer entity to add to the database
     */
    @Override
    public void create(Customer customer) {
        entityManager.persist(customer);
    }

    /**
     * Updates a row in the customers table
     *
     * @param customer Customer entity to update in the database
     */
    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    /**
     * Returns the list of all customers in customers table
     *
     * @return {@link List} of {@link Customer}
     */
    @Override
    public List<Customer> getAll() {
        return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    /**
     * Returns the customer by email
     *
     * @param email Email of customer to get from the database
     * @return {@link Customer}
     */
    @Override
    public Customer getOne(String email) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "select c from Customer c where c.email = :email",
                Customer.class
        );
        query.setParameter("email", email);
        return query.getResultList().stream().findAny().orElse(null);
    }

    /**
     * Returns the list of the top ten customers
     *
     * @return {@link List} of {@link CustomerStatistics}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<CustomerStatistics> getTopTenCustomers() {
        List<Object[]> result = entityManager.createQuery("select c.firstName, c.lastName, c.email," +
                " sum (o.total) as purchasesTotal" +
                " from Customer c" +
                " left join Order o on o.customer.id = c.id" +
                " where o.paymentStatus = :paymentStatus" +
                " and o.total > 0" +
                " group by c.id" +
                " order by purchasesTotal desc")
                .setParameter("paymentStatus", PaymentStatus.PAID)
                .setMaxResults(10)
                .getResultList();

        if (result == null) {
            return new ArrayList<>();
        }

        List<CustomerStatistics> customersStatistics = new ArrayList<>();

        for (Object[] objects : result) {
            CustomerStatistics customerStatistics = new CustomerStatistics();
            customerStatistics.setFirstName((String) objects[0]);
            customerStatistics.setLastName((String) objects[1]);
            customerStatistics.setEmail((String) objects[2]);
            customerStatistics.setPurchasesTotal((Long) objects[3]);
            customersStatistics.add(customerStatistics);
        }

        return customersStatistics;
    }
}
