package com.marsarmy.service.interf;

import com.marsarmy.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAll();

    Role getOne(String name);
}
