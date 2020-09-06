package com.marsarmy.service.interf;

import com.marsarmy.model.Role;

import java.util.List;

/**
 * Service interface responsible for operations on roles
 */
public interface RoleService {

    List<Role> getAll();

    Role getOne(String name);
}
