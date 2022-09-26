package de.admanagement.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class Ad {

    private Long id;

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    private String description;

    @NotNull
    private Category category;

    @Positive
    private BigDecimal price;

    @NotNull
    private Long customerId;
}
