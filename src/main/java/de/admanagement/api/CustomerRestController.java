package de.admanagement.api;

import de.admanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerRestController implements CustomerResource {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<Customer> getCustomer(Long customerId) {
        return ResponseEntity.of(customerService.get(customerId));
    }

    @Override
    public Long createCustomer(Customer customer) {
        return customerService.create(customer).getId();
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerService.delete(customerId);
    }

    @Override
    public List<Customer> list() {
        return customerService.list();
    }
}
