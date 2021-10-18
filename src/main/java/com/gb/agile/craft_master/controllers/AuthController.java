package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.config.Const;
import com.gb.agile.craft_master.config.JwtFilter;
import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.services.UserService;
import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.model.dtos.AuthRequestDto;
import com.gb.agile.craft_master.model.dtos.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/user_login")
    public AuthResponseDto login(@RequestBody AuthRequestDto req) {
        User user = userService.getByLoginAndPassword(req.getLogin(), req.getPassword());
        String token = jwtProvider.generateToken(user.getId(), user.getLogin(), user.getRole().getCode());
        return new AuthResponseDto(token);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/user_logout")
    public String logout(@RequestHeader(Const.AUTHORIZATION) String bearer) {
        jwtProvider.setTokenLogout(JwtFilter.getFromHeader(bearer));
        return "OK";
    }

}
