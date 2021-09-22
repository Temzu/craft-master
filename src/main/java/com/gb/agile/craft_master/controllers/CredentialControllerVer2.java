package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.core.interfaces.CredentialService;
import com.gb.agile.craft_master.core.interfaces.UserServiceVer2;
import com.gb.agile.craft_master.model.Credential;
import com.gb.agile.craft_master.model.dto.CredentialDto;
import com.gb.agile.craft_master.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/credentials")
public class CredentialControllerVer2 {

    private final CredentialService credentialService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public List<Credential> getAll() {
        Integer userId = JwtProvider.getUserId();
        return credentialService.getAllCredentialByUserId(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    public Credential addCredential(@RequestBody CredentialDto credential) {
        Integer userId = JwtProvider.getUserId();
        return credentialService.addCredential(userId, credential);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{code}")
    public void deleteCredential(@PathVariable String code) {
        Integer userId = JwtProvider.getUserId();
        credentialService.deleteCredential(userId, code);
    }
}