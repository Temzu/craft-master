package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}