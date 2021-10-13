package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.services.interfaces.CredentialService;
import com.gb.agile.craft_master.model.entities.Credential;
import com.gb.agile.craft_master.model.dtos.CredentialDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CredentialServiceImplTest {

    @Autowired
    private CredentialService credentialService;

    @Test
    public void whenGetAllCredentials_thenFindShouldBeSuccessful() {
        List<Credential> list = credentialService.getAllCredentialByUserId(Long.valueOf(2));
        assertNotNull(list);
    }

    @Test
    public void whenAddCredentials_thenCountShouldBeIncrease() {
        int count = credentialService.getAllCredentialByUserId(Long.valueOf(2)).size();
        credentialService.addCredential(Long.valueOf(2), new CredentialDto("mail", "a@a.a", "e-mail"));
        assertEquals(++count,  credentialService.getAllCredentialByUserId(Long.valueOf(2)).size());
    }

    @Test
    public void whenDelCredentials_thenCountShouldBeIncrease() {
        List<Credential> list = credentialService.getAllCredentialByUserId(Long.valueOf(2));
        int count = list.size();
        credentialService.deleteCredential(Long.valueOf(2),list.get(0).getCode());
        assertEquals(--count,  credentialService.getAllCredentialByUserId(Long.valueOf(2)).size());
    }
}