package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(Integer id);

    Role getRoleByCode(String code);

    Role addRole(Role role);

    void deleteRoleById(Integer id);
}
