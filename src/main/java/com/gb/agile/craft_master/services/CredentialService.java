package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.model.entities.Credential;
import com.gb.agile.craft_master.model.dtos.CredentialDto;

import java.util.List;

public interface CredentialService {

    List<Credential> getAllCredentialByUserId(Long userId);

    Credential addCredential(Long userId, CredentialDto credentialDto);

    void deleteCredential(Long userId, String code);
}
