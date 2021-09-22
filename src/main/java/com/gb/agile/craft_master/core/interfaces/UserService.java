package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.User;
import com.gb.agile.craft_master.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Integer id);

    User getByLogin(String login);

    User getByLoginAndPassword(String login, String password);

    User addUser(UserDto userDto);

    User updateUser(Integer id, UserDto userDto);

    void deleteUserById(Integer id);

}
