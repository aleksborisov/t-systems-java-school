package com.marsarmy.statistics;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerStatistics {

    private String firstName;
    private String lastName;
    private String email;
    private Long purchasesTotal;
}
