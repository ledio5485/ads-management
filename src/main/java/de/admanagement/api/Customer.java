package de.admanagement.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private Long id;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")
    private String lastName;

    private String companyName;

    @Email
    private String email;
}
