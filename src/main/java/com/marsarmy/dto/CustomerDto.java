package com.marsarmy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private Long id;
    private String role;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;

    private List<AddressDto> addressesDto;
    private List<OrderDto> ordersDto;
}
