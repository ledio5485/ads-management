package de.admanagement.service;

import de.admanagement.api.Customer;
import de.admanagement.persistence.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements Converter<Customer, CustomerEntity> {

    @Override
    public Customer outbound(CustomerEntity customerEntity) {
        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setFirstName(customerEntity.getFirstName());
        customer.setLastName(customerEntity.getLastName());
        customer.setCompanyName(customerEntity.getCompanyName());
        customer.setEmail(customerEntity.getEmail());
        return customer;
    }

    @Override
    public CustomerEntity inbound(Customer customerData) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerData.getId());
        customerEntity.setFirstName(customerData.getFirstName());
        customerEntity.setLastName(customerData.getLastName());
        customerEntity.setCompanyName(customerData.getCompanyName());
        customerEntity.setEmail(customerData.getEmail());
        return customerEntity;
    }
}
