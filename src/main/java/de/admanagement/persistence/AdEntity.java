package de.admanagement.persistence;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "AD")
@Data
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String description;
    private Category category;
    private BigDecimal price;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private CustomerEntity customerEntity;
}
