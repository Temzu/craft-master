package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.services.interfaces.CredentialService;
import com.gb.agile.craft_master.model.entities.Credential;
import com.gb.agile.craft_master.model.dtos.CredentialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credentials")
@PreAuthorize("isAuthenticated()")
public class CredentialController {

    private final CredentialService credentialService;

    @GetMapping
    public List<Credential> getAll() {
        Long userId = JwtProvider.getUserId();
        return credentialService.getAllCredentialByUserId(userId);
    }

    @PostMapping
    public Credential addCredential(@RequestBody CredentialDto credential) {
        Long userId = JwtProvider.getUserId();
        return credentialService.addCredential(userId, credential);
    }

    @DeleteMapping("/{code}")
    public void deleteCredential(@PathVariable String code) {
        Long userId = JwtProvider.getUserId();
        credentialService.deleteCredential(userId, code);
    }
}
