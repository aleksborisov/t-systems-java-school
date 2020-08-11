package com.marsarmy.dao.impl;

import com.marsarmy.dao.interf.CustomerDao;
import com.marsarmy.dto.CustomerStatisticsDto;
import com.marsarmy.model.Customer;
import com.marsarmy.model.enumeration.PaymentStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void create(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public List<Customer> getAll() {
        return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer getOne(String email) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "select c from Customer c where c.email = :email",
                Customer.class
        );
        query.setParameter("email", email);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CustomerStatisticsDto> getTopTenCustomers() {
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

        List<CustomerStatisticsDto> customersStatisticsDto = new ArrayList<>();

        for (Object[] objects : result) {
            CustomerStatisticsDto customerStatisticsDto = new CustomerStatisticsDto();
            customerStatisticsDto.setFirstName((String) objects[0]);
            customerStatisticsDto.setLastName((String) objects[1]);
            customerStatisticsDto.setEmail((String) objects[2]);
            customerStatisticsDto.setPurchasesTotal((Long) objects[3]);
            customersStatisticsDto.add(customerStatisticsDto);
        }

        return customersStatisticsDto;
    }
}
