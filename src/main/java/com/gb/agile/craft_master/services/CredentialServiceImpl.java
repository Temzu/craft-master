package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.CredentialService;
import com.gb.agile.craft_master.core.interfaces.UserServiceVer2;
import com.gb.agile.craft_master.model.Credential;
import com.gb.agile.craft_master.model.User;
import com.gb.agile.craft_master.model.dto.CredentialDto;
import com.gb.agile.craft_master.repositories.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;

    private final UserServiceVer2 userService;

    @Transactional
    @Override
    public List<Credential> getAllCredentialByUserId(Integer userId) {
        User user = userService.getUserById(userId);
        if (user == null) throw new RuntimeException("User not found");
        return user.getCredentials();
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
