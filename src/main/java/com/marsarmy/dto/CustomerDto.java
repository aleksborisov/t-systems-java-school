package com.marsarmy.dto;

import com.marsarmy.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO class of {@link Customer} entity
 */
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
}
