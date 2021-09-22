package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.RoleService;
import com.gb.agile.craft_master.core.interfaces.UserService;
import com.gb.agile.craft_master.model.RoleCodes;
import com.gb.agile.craft_master.model.User;
import com.gb.agile.craft_master.model.dtos.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceVer2ImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Test
    public void whenFindById_thenFindShouldBeSuccessful() {
        User user = userService.getUserById(1);
        assertEquals(1, user.getId());
    }

    @Test
    public void whenFindByLoginAndPassword_thenFindShouldBeSuccessful() {
        User user = userService.getByLoginAndPassword("ivan", "123");
        assertEquals(1, user.getId());
    }

    @Test
    public void whenFindAll_thenFindShouldBeSuccessful() {
        List<User> list = userService.getAllUsers();
        assertNotNull(list);
    }

    @Test
    public void whenAddUser_thenCountShouldBeIncrease() {
        int count = userService.getAllUsers().size();
        User user = userService.addUser(
                new UserDto("test", "test", "test", roleService.getRoleByCode(RoleCodes.ROLE_USER.name())));
        assertEquals("test", user.getLogin());
        assertEquals(++count, userService.getAllUsers().size());
    }

    @Test
    public void whenUpdateUser_thenNameShouldBeChanged() {
        User user = userService.addUser(
                new UserDto("test", "test", "test", roleService.getRoleByCode(RoleCodes.ROLE_USER.name())));
        assertNotEquals(user, userService.updateUser(user.getId(),
                new UserDto("test2", "test2", "test2", roleService.getRoleByCode(RoleCodes.ROLE_USER.name()))));
    }

    @Test
    public void whenDeleteUser_thenCountShouldBeTheSame() {
        int count = userService.getAllUsers().size();
        User user = userService.addUser(
                new UserDto("test", "test", "test", roleService.getRoleByCode(RoleCodes.ROLE_USER.name())));
        userService.deleteUserById(user.getId());
        assertEquals(count, userService.getAllUsers().size());
    }
}