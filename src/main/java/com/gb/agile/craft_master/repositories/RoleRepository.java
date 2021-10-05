package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByCode(String code);
}