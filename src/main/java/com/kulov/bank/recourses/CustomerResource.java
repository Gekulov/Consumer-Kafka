package com.kulov.bank.recourses;


import com.kulov.bank.model.Customer;
import com.kulov.bank.dao.CustomerDAO;
import com.kulov.bank.service.CustomerService;
import io.dropwizard.hibernate.UnitOfWork;
import org.glassfish.jersey.internal.inject.Custom;


import javax.inject.Inject;
import javax.persistence.EntityListeners;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)

public class CustomerResource {

    private CustomerService customerService;

    @Inject
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    @UnitOfWork
    public List getAll() {
        return customerService.getAllCustomers();
    }

    @GET
    @Path("/nickname/{nickname}")
    @UnitOfWork
    public List getCustomerByNickname(@PathParam("nickname") String nickname){
        return customerService.getByNickname(nickname);
    }
    @GET
    @Path("/{id}")
    @UnitOfWork
    public Customer getCustomerById(@PathParam("id") long id) {
        return customerService.getById(id);
    }

    @POST
    @UnitOfWork
    @Path("/add")
    public Customer addCustomer(Customer customer) {

        return customerService.createCustomer(customer);
    }


    @POST
    @UnitOfWork
    @Path("/delete/{id}")
    public Customer softDeleteCustomer(@PathParam(value = "id") long id) {
        Customer customer = getCustomerById(id);
        customerService.softDeleteCustomer(customer);
        return customer;
    }

    @PUT
    @UnitOfWork
    @Path("/update/{id}")
    public Customer updateCustomer(@PathParam("id") long id, Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
}
