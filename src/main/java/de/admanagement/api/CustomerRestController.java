package de.admanagement.api;

import de.admanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerRestController implements Resource<Customer, Long> {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<Customer> findById(Long id) {
        return ResponseEntity.of(customerService.get(id));
    }

    @Override
    public ResponseEntity<Customer> create(Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
    }

    @Override
    public void deleteById(Long id) {
        customerService.delete(id);
    }

    @Override
    public ResponseEntity<Collection<Customer>> findAll() {
        return ResponseEntity.ok(customerService.list());
    }
}
