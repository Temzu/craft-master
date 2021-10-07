package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.RoleService;
import com.gb.agile.craft_master.core.interfaces.UserService;
import com.gb.agile.craft_master.exceptions.LoginFailedException;
import com.gb.agile.craft_master.core.enums.RoleCodes;
import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.model.dtos.UserDto;
import com.gb.agile.craft_master.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getProxyById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        User userEntity = getByLogin(login);
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

    @Transactional
    @Override
    public User addUser(UserDto userDto) {
        userDto.setRole(roleService.getRoleByCode(RoleCodes.ROLE_USER.name()));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(new User(userDto));
    }

    @Transactional
    @Override
    public User updateUser(Long id, UserDto userDto) {
        User updatableUser = getUserById(id);
        if (updatableUser == null) throw new RuntimeException("User not found");
        updatableUser.setLogin(userDto.getLogin());
        updatableUser.setName(userDto.getName());
        updatableUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(updatableUser);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
