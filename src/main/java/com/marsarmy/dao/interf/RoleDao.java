package com.marsarmy.dao.interf;

import com.marsarmy.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getAll();

    Role getOne(String name);
}
