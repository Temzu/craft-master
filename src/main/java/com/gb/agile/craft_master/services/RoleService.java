package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.model.entities.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role getRoleByCode(String code);

    Role addRole(Role role);

    void deleteRoleById(Long id);
}
