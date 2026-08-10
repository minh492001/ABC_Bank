package com.abc.abcbank.role.service;

import com.abc.abcbank.response.Response;
import com.abc.abcbank.role.entity.Role;

import java.util.List;

public interface RoleService {

    Response<Role> createRole(Role roleRequest);
    Response<Role> updateRole(Role roleRequest);
    Response<List<Role>> getAllRoles();
    Response<?> deleteRole(Long id);
}
