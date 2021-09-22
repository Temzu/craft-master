package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.core.interfaces.UserService;
import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.model.dtos.UserDto;
import com.gb.agile.craft_master.model.dtos.UserInfoDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    public User addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody UserDto user) {
        return userService.updateUser(id, user);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user_info")
    public UserInfoDto getUserInfo() {
        Integer userId = JwtProvider.getUserId();
        return new UserInfoDto(userService.getUserById(userId));
    }
}
