package de.admanagement.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.admanagement.service.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerResource.class)
class CustomerResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Nested
    @DisplayName("Tests for the method getCustomer")
    class GetCustomer {
        @Test
        @DisplayName("should return NOT_FOUND when customer does not exist")
        void shouldReturnNotFoundWhenCustomerDoesNotExist() throws Exception {
            mockMvc.perform(get("/customers/{id}", 42L)
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("should get customer when it exists")
        void shouldGetCustomerWhenExist() throws Exception {
            Customer expectedCustomer = Customer.builder()
                    .id(1L)
                    .firstName("name")
                    .lastName("surname")
                    .companyName("company.de")
                    .email("name.surname@company.de")
                    .build();

            when(customerService.get(expectedCustomer.getId()))
                    .thenReturn(Optional.of(expectedCustomer));

            MvcResult mvcResult = mockMvc.perform(get("/customers/{id}", expectedCustomer.getId())
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();

            String actualResponseBody = mvcResult.getResponse().getContentAsString();
            String expectedResponseBody = objectMapper.writeValueAsString(expectedCustomer);
            assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
        }
    }

    @Nested
    @DisplayName("Tests for the method createCustomer")
    class CreateCustomer {
        @Test
        @DisplayName("should get BAD_REQUEST when saving new customer with missing mandatory field")
        void shouldGetBadRequestWhenSavingNewCustomerWithMissingMandatoryField() throws Exception {
            Customer customer = Customer.builder()
                    .lastName("surname")
                    .companyName("company.de")
                    .email("name.surname@company.de")
                    .build();

            mockMvc.perform(post("/customers")
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .content(objectMapper.writeValueAsString(customer)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("should get CREATED when saving new customer with all mandatory fields")
        void shouldGetCreatedWhenSavingNewCustomerWithAllMandatoryField() throws Exception {
            Customer customer = Customer.builder()
                    .id(1L)
                    .firstName("name")
                    .lastName("surname")
                    .companyName("company.de")
                    .email("name.surname@company.de")
                    .build();

            when(customerService.create(customer))
                    .thenReturn(customer);

            MvcResult mvcResult = mockMvc.perform(post("/customers")
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .content(objectMapper.writeValueAsString(customer)))
                    .andExpect(status().isCreated())
                    .andReturn();

            String actualResponseBody = mvcResult.getResponse().getContentAsString();
            assertThat(actualResponseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(customer));
        }
    }
}
