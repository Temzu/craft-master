package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}