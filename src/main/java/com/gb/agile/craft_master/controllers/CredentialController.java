package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.model.Credential;
import com.gb.agile.craft_master.model.User;
import com.gb.agile.craft_master.model.dto.CredentialDto;
import com.gb.agile.craft_master.model.dto.UserDto;
import com.gb.agile.craft_master.model.dto.UserInfoDto;
import com.gb.agile.craft_master.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credentials")
public class CredentialController {

    private final UserService userService;

    public CredentialController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public List<Credential> getAll() {
        Integer userId = JwtProvider.getUserId();
        return userService.getAllCredentials(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    public Credential addCredential(@RequestBody CredentialDto credential) {
        Integer userId = JwtProvider.getUserId();
        return userService.addCredential(userId, credential);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{code}")
    public void deleteCredential(@PathVariable String code) {
        Integer userId = JwtProvider.getUserId();
        userService.deleteCredential(userId, code);
    }
}
