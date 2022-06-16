package com.kulov.bank.dao;

import com.kulov.bank.model.Customer;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.persistence.Query;
import java.util.List;


public class CustomerDAO extends AbstractDAO<Customer> {

    @Inject
    public CustomerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Customer> getAll() {
        Query query = currentSession().createQuery("from Customer ");

        return query.getResultList();
    }

    public Customer findById(long id) {
        return get(id);
    }

    public List findByMail(String nickname) {
        Query query = currentSession().createQuery("from Customer where nickname = :nickname ");
        query.setParameter("nickname", nickname);
        return query.getResultList();
    }

    public Customer createCustomer(Customer customer) {
        return persist(customer);
    }

    public void softDeleteCustomer(Customer customer) {
        persist(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return persist(customer);
    }
}
