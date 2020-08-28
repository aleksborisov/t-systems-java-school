package com.marsarmy.dto;

import com.marsarmy.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO class of {@link Role} entity
 */
@NoArgsConstructor
@Getter
@Setter
public class RoleDto {

    private Long id;
    private String name;
}
