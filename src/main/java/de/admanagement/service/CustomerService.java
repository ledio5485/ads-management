package de.admanagement.service;

import de.admanagement.api.Customer;
import de.admanagement.persistence.CustomerRepository;
import de.admanagement.persistence.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Transactional
    public Customer create(Customer customerData) {
        CustomerEntity customerEntity = customerRepository.save(customerConverter.inbound(customerData));
        return customerConverter.outbound(customerEntity);
    }

    public Optional<Customer> get(Long customerId) {
        return customerConverter.outbound(customerRepository.findById(customerId));
    }

    @Transactional
    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public List<Customer> list() {
        return customerConverter.outbound(customerRepository.findAll());
    }
}
