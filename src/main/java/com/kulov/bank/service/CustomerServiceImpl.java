package com.kulov.bank.service;

import com.kulov.bank.dao.CustomerDAO;
import com.kulov.bank.model.Customer;

import javax.inject.Inject;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {


    private final CustomerDAO customerDAO;

    @Inject
    public CustomerServiceImpl(CustomerDAO customerDAO) {

        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customer> getAllCustomers() {

        return customerDAO.getAll();
    }

    @Override
    public Customer getById(long id) {
        return customerDAO.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDAO.createCustomer(customer);
    }

    @Override
    public void softDeleteCustomer(Customer customer) {
        customer.setActive(false);
        customerDAO.softDeleteCustomer(customer);
    }

    @Override
    public Customer updateCustomer(long id, Customer newCustomer) {
        Customer customer = customerDAO.findById(id);
        customer.setFirstName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());
        customer.setNickname(newCustomer.getNickname());
        return customerDAO.updateCustomer(newCustomer);
    }

    @Override
    public List getByNickname(String nickname) {
        return customerDAO.findByMail(nickname);
    }

}
