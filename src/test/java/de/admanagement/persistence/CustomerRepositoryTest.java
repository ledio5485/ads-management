package de.admanagement.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldGetEmptyEntityWhenCustomerIsNotFound() {
        Optional<CustomerEntity> maybeCustomer = customerRepository.findById(1L);

        assertThat(maybeCustomer).isEmpty();
    }

    @Test
    void shouldGetExistingCustomer() {
        CustomerEntity customer = CustomerEntity.builder()
                .firstName("name")
                .lastName("surname")
                .companyName("company.de")
                .email("name.surname@company.de")
                .build();
        CustomerEntity expectedCustomer = customerRepository.save(customer);

        CustomerEntity actualCustomer = customerRepository.getOne(expectedCustomer.getId());

        assertThat(actualCustomer).isEqualTo(expectedCustomer);
    }
}