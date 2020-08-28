package com.marsarmy.service.impl;

import com.marsarmy.dao.interf.RoleDao;
import com.marsarmy.model.Role;
import com.marsarmy.service.interf.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service responsible for operations on roles
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * Returns the list of all roles
     *
     * @return {@link List} of {@link Role}
     */
    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    /**
     * Returns the role by id
     *
     * @param name Id of role to get
     * @return {@link Role}
     */
    @Override
    public Role getOne(String name) {
        return roleDao.getOne(name);
    }
}
