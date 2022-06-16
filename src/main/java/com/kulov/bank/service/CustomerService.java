package com.kulov.bank.service;

import com.kulov.bank.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getById(long id);

    Customer createCustomer(Customer customer);

    void softDeleteCustomer(Customer customer);

    Customer updateCustomer(long id, Customer newCustomer);

    List getByNickname(String nickname);
}
