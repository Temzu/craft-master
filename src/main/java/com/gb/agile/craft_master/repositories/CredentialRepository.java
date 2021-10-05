package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    List<Credential> getAllByUserId(Long id);

    void deleteCredentialByUserIdAndCode(Long userId, String code);
}