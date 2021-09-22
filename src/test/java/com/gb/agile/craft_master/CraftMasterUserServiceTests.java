package com.gb.agile.craft_master;

import com.gb.agile.craft_master.model.Credential;
import com.gb.agile.craft_master.model.User;
import com.gb.agile.craft_master.model.dto.CredentialDto;
import com.gb.agile.craft_master.model.dto.UserDto;
import com.gb.agile.craft_master.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CraftMasterUserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void whenFindById_thenFindShouldBeSuccessful() {
        User user = userService.findById(1);
        assertEquals(1, user.getId());
    }

    @Test
    public void whenFindByLoginAndPassword_thenFindShouldBeSuccessful() {
        User user = userService.findByLoginAndPassword("ivan", "123");
        assertEquals(1, user.getId());
    }

    @Test
    public void whenFindAll_thenFindShouldBeSuccessful() {
        List<User> list = userService.findAll();
        assertNotNull(list);
    }

    @Test
    public void whenAddUser_thenCountShouldBeIncrease() {
        int count = userService.findAll().size();
        User user = userService.addUser(
                new UserDto("test", "test", "test", userService.getDefaultRole()));
        assertEquals("test", user.getLogin());
        assertEquals(++count, userService.findAll().size());
    }

    @Test
    public void whenUpdateUser_thenNameShouldBeChanged() {
        User user = userService.addUser(
                new UserDto("test", "test", "test", userService.getDefaultRole()));
        assertNotEquals(user, userService.updateUser(user.getId(),
                new UserDto("test2", "test2", "test2", userService.getDefaultRole())));
    }

    @Test
    public void whenDeleteUser_thenCountShouldBeTheSame() {
        int count = userService.findAll().size();
        User user = userService.addUser(
                new UserDto("test", "test", "test", userService.getDefaultRole()));
        userService.deleteById(user.getId());
        assertEquals(count, userService.findAll().size());
    }

    @Test
    public void whenGetAllCredentials_thenFindShouldBeSuccessful() {
        List<Credential> list = userService.getAllCredentials(2);
        assertNotNull(list);
    }

    @Test
    public void whenAddCredentials_thenCountShouldBeIncrease() {
        int count = userService.getAllCredentials(2).size();
        userService.addCredential(2, new CredentialDto("mail", "a@a.a", "e-mail"));
        assertEquals(++count,  userService.getAllCredentials(2).size());
    }

    @Test
    @Transactional
    public void whenDelCredentials_thenCountShouldBeIncrease() {
        List<Credential> list = userService.getAllCredentials(2);
        int count = list.size();
        userService.deleteCredential(2,list.get(0).getCode());
        assertEquals(--count,  userService.getAllCredentials(2).size());
    }
}
