package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {

    List<Credential> getAllByUserId(Integer id);

    void deleteCredentialByUserIdAndCode(Integer userId, String code);
}