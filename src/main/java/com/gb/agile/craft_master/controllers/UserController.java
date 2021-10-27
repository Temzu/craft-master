package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.core.enums.StatusCode;
import com.gb.agile.craft_master.exceptions.LoginExistsException;
import com.gb.agile.craft_master.model.dtos.*;
import com.gb.agile.craft_master.services.CredentialService;
import com.gb.agile.craft_master.services.UserService;
import com.gb.agile.craft_master.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CredentialService credentialService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public User addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @PostMapping("/registr")
    public StatusDto registrUser(@RequestBody RegistrUserDto registrUserDto) {
        try {
            User newUser = userService.addUser(new UserDto(registrUserDto.getLogin(), registrUserDto.getName(), registrUserDto.getPassword(), null));
            for (CredentialDto credentialDto : registrUserDto.getCredentialDtos()) {
                credentialService.addCredential(newUser.getId(), credentialDto);
            }
            return new StatusDto(StatusCode.STATUS_OK);
        } catch (LoginExistsException e) {
            System.out.println(e.getMessage());
            return new StatusDto(StatusCode.LOGIN_EXISTS);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        return userService.updateUser(id, user);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user_info")
    public UserInfoDto getUserInfo() {
        Long userId = JwtProvider.getUserId();
        return new UserInfoDto(userService.getUserById(userId));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/customer_info/{id}")
    public UserInfoDto getCustomerInfo(@PathVariable Long id) {
        return new UserInfoDto(userService.getUserById(id));
    }
}
