package com.marsarmy.dao.interf;

import com.marsarmy.model.Role;

import java.util.List;

/**
 * DAO interface of Role entity
 */
public interface RoleDao {

    List<Role> getAll();

    Role getOne(String name);
}
