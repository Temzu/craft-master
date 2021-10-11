package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.RoleService;
import com.gb.agile.craft_master.model.entities.Role;
import com.gb.agile.craft_master.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    //TODO
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    //TODO
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    //TODO
    @Override
    public Role getRoleByCode(String code) {
        return roleRepository.findByCode(code);
    }
    //TODO
    @Override
    public Role addRole(Role role) {
        return null;
    }
    //TODO
    @Override
    public void deleteRoleById(Long id) {

    }
}
