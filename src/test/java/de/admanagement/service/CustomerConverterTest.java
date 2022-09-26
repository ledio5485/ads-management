package de.admanagement.service;

import de.admanagement.api.Customer;
import de.admanagement.persistence.CustomerEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerConverterTest {
    private final CustomerConverter customerConverter = new CustomerConverter();

    @Test
    void shouldConvertDtoToModel() {
        Customer customer = createCustomer();

        CustomerEntity actual = customerConverter.inbound(customer);

        CustomerEntity expected = createCustomerEntity();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldConvertModelToDto() {
        CustomerEntity customer = createCustomerEntity();

        Customer actual = customerConverter.outbound(customer);

        Customer expected = createCustomer();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldConvertOptionalModelToDto() {
        CustomerEntity customer = createCustomerEntity();

        Optional<Customer> actual = customerConverter.outbound(Optional.of(customer));

        Optional<Customer> expected = Optional.of(createCustomer());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldConvertModelsToDtos() {
        CustomerEntity customer = createCustomerEntity();

        List<Customer> actual = customerConverter.outbound(List.of(customer));

        List<Customer> expected = List.of(createCustomer());
        assertThat(actual).isEqualTo(expected);
    }

    private CustomerEntity createCustomerEntity() {
        return CustomerEntity.builder()
                .id(1L)
                .firstName("name")
                .lastName("surname")
                .companyName("company.de")
                .email("name.surname@company.de")
                .build();
    }

    private Customer createCustomer() {
        return Customer.builder()
                .id(1L)
                .firstName("name")
                .lastName("surname")
                .companyName("company.de")
                .email("name.surname@company.de")
                .build();
    }
}