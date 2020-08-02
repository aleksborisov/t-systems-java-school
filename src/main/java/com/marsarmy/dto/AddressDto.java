package com.marsarmy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddressDto {

    private Long id;
    private String customerDto;
    private String country;
    private String city;
    private String zipCode;
    private String street;
    private Integer building;
    private Integer apartment;
}
