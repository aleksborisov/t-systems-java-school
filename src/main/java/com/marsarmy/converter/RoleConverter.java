package com.marsarmy.converter;

import com.marsarmy.dto.RoleDto;
import com.marsarmy.model.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleConverter {

    public RoleDto convertToDto(Role role) {
        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setName(role.getName());

        return roleDto;
    }

    public Role convertToEntity(RoleDto roleDto) {
        Role role = new Role();

        role.setId(roleDto.getId());
        role.setName(roleDto.getName());

        return role;
    }

    public List<RoleDto> convertToListOfDto(List<Role> roles) {
        List<RoleDto> rolesDto = new ArrayList<>();
        for (Role role : roles) {
            rolesDto.add(convertToDto(role));
        }
        return rolesDto;
    }

    public List<Role> convertToListOfEntity(List<RoleDto> rolesDto) {
        List<Role> roles = new ArrayList<>();
        for (RoleDto roleDto : rolesDto) {
            roles.add(convertToEntity(roleDto));
        }
        return roles;
    }
}
