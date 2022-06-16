package com.kulov.bank;

import com.kulov.bank.dao.CustomerDAO;
import com.kulov.bank.model.Customer;
import com.kulov.bank.recourses.CustomerResource;
import com.kulov.bank.service.CustomerService;
import com.kulov.bank.service.CustomerServiceImpl;
import com.kulov.bank.service.KafkaConsumerService;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;


public class BankApplication extends Application<BankConfiguration> {


    private final HibernateBundle<BankConfiguration> hibernateBundle = new HibernateBundle<BankConfiguration>(Customer.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(BankConfiguration bankConfiguration) {
            return bankConfiguration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new BankApplication().run(args);
    }


    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void initialize(final Bootstrap<BankConfiguration> bootstrap) {
        bootstrap.addBundle(this.hibernateBundle);
    }

    @Override
    public void run(final BankConfiguration configuration,
                    final Environment environment) {


        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                final CustomerDAO customerDAO = new CustomerDAO(hibernateBundle.getSessionFactory());
                bind(customerDAO);
                bindAsContract(CustomerServiceImpl.class).to(CustomerService.class).in(Singleton.class);

            }
        });
        KafkaConsumerService service = new KafkaConsumerService(configuration.getKafkaConsumerConfiguration(),
                configuration.getTopics());
        environment.jersey().register(CustomDeserializerFactory.class);
        environment.jersey().register(CustomerResource.class);
        environment.lifecycle().manage(service);
        environment.jersey().register(service);
        System.out.println("List of topics: " + configuration.getTopics());
    }

}
