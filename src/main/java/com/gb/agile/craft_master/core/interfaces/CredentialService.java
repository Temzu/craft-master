package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.entities.Credential;
import com.gb.agile.craft_master.model.dtos.CredentialDto;

import java.util.List;

public interface CredentialService {

    List<Credential> getAllCredentialByUserId(Integer userId);

    Credential addCredential(Integer userId, CredentialDto credentialDto);

    void deleteCredential(Integer userId, String code);
}
