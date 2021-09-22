package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.CredentialService;
import com.gb.agile.craft_master.model.Credential;
import com.gb.agile.craft_master.model.dtos.CredentialDto;
import com.gb.agile.craft_master.repositories.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;

    @Transactional
    @Override
    public List<Credential> getAllCredentialByUserId(Integer userId) {
        return credentialRepository.getAllByUserId(userId);
    }

    @Transactional
    @Override
    public Credential addCredential(Integer userId, CredentialDto credentialDto) {
        return credentialRepository.save(new Credential(userId, credentialDto));
    }

    @Transactional
    @Override
    public void deleteCredential(Integer userId, String code) {
        credentialRepository.deleteCredentialByUserIdAndCode(userId, code);
    }
}
