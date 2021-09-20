package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {
}