package de.admanagement.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.admanagement.persistence.CustomerRepository;
import de.admanagement.persistence.CustomerEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerRestControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("should return NOT_FOUND when customer does not exist")
    void shouldReturnNotFoundWhenCustomerDoesNotExist() throws Exception {
        mockMvc.perform(get("/customers/{id}", 42L)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should get a customer when it exists")
    void shouldGetAnExistingCustomer() throws Exception {
        CustomerEntity customer = CustomerEntity.builder()
                .firstName("name")
                .lastName("surname")
                .companyName("company.de")
                .email("name.surname@company.de")
                .build();
        CustomerEntity savedCustomer = customerRepository.save(customer);

        MvcResult mvcResult = mockMvc.perform(get("/customers/{id}", savedCustomer.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        Customer expectedCustomer = Customer.builder()
                .id(1L)
                .firstName("name")
                .lastName("surname")
                .companyName("company.de")
                .email("name.surname@company.de")
                .build();
        String expectedResponseBody = objectMapper.writeValueAsString(expectedCustomer);
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }
}