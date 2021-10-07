package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.model.dtos.UserDto;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getProxyById(Long id);

    User getByLogin(String login);

    User getByLoginAndPassword(String login, String password);

    User addUser(UserDto userDto);

    User updateUser(Long id, UserDto userDto);

    void deleteUserById(Long id);

}
