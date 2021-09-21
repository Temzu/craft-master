package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.exceptions.LoginFailedException;
import com.gb.agile.craft_master.model.Credential;
import com.gb.agile.craft_master.model.Role;
import com.gb.agile.craft_master.model.RoleCodes;
import com.gb.agile.craft_master.model.User;
import com.gb.agile.craft_master.model.dto.CredentialDto;
import com.gb.agile.craft_master.model.dto.UserDto;
import com.gb.agile.craft_master.repositories.CredentialRepository;
import com.gb.agile.craft_master.repositories.RoleRepository;
import com.gb.agile.craft_master.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CredentialRepository credentialRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, CredentialRepository credentialRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.credentialRepository = credentialRepository;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User addUser(UserDto user) {
        user.setRole(getDefaultRole());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(new User(user));
    }

    public Role getDefaultRole() {
        return roleRepository.findByCode(RoleCodes.ROLE_USER.toString());
    }

    @Transactional
    public User updateUser(Integer id, UserDto user) {
        User newUser = findById(id);
        if (newUser == null) throw new RuntimeException("User not found");
        newUser.setLogin(user.getLogin());
        newUser.setName(user.getName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    @Transactional
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public List<Credential> getAllCredentials(Integer id) {
        User user = findById(id);
        if (user == null) throw new RuntimeException("User not found");
        return user.getCredentials();
    }

    @Transactional
    public Credential addCredential(Integer userId, CredentialDto credential) {
        return credentialRepository.save(new Credential(userId, credential));
    }

    @Transactional
    public void deleteCredential(Integer id, String code) {
        List<Credential> list = getAllCredentials(id);
        if (list == null) return;
        list.stream().filter(c -> c.getCode().equals(code)).findFirst().ifPresent(credentialRepository::delete);
    }
}
