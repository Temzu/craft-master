package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.exceptions.LoginFailedException;
import com.gb.agile.craft_master.model.User;
import com.gb.agile.craft_master.repositories.RoleRepository;
import com.gb.agile.craft_master.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(Integer id) {
        return userRepository.getById(id);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            } else {
                throw new LoginFailedException("Incorrect login and password");
            }
        } else {
            throw new LoginFailedException("User is not found");
        }
    }
}
