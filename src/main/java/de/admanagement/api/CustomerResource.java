package de.admanagement.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("customers")
public interface CustomerResource {
    @GetMapping(value = "{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable("id") Long customerId);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Long createCustomer(@RequestBody @Valid Customer customer);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable("id") Long customerId);

    @GetMapping
    List<Customer> list();
}
