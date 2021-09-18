package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.config.Const;
import com.gb.agile.craft_master.config.JwtFilter;
import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.model.dto.AuthRequestDto;
import com.gb.agile.craft_master.model.dto.AuthResponseDto;
import com.gb.agile.craft_master.model.User;
import com.gb.agile.craft_master.model.dto.UserInfoDto;
import com.gb.agile.craft_master.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;
    private JwtProvider jwtProvider;

    @Autowired
    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/user_login")
    public AuthResponseDto login(@RequestBody AuthRequestDto req) {
        User user = userService.findByLoginAndPassword(req.getLogin(), req.getPassword());
        String token = jwtProvider.generateToken(user.getId(), user.getLogin(), user.getRole().getCode());
        return new AuthResponseDto(token);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/user_logout")
    public String logout(@RequestHeader(Const.AUTHORIZATION) String bearer) {
        jwtProvider.setTokenLogout(JwtFilter.getFromHeader(bearer));
        return "OK";
    }

    //Переместить в другой контроллер, когда он будет
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/user_info")
//    public UserInfoDto getUserInfo() {
//        Integer userId = JwtProvider.getUserId();
//        return new UserInfoDto(userService.findById(userId));
//    }
}
