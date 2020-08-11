package com.marsarmy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerStatisticsDto {

    private String firstName;
    private String lastName;
    private String email;
    private Long purchasesTotal;
}
