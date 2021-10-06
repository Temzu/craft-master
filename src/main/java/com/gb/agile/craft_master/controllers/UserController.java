package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.core.interfaces.UserService;
import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.model.dtos.UserDto;
import com.gb.agile.craft_master.model.dtos.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public User addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/user_info")
    public UserInfoDto getUserInfo() {
        Long userId = JwtProvider.getUserId();
        return new UserInfoDto(userService.getUserById(userId));
    }
}
