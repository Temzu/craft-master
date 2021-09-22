package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.CredentialService;
import com.gb.agile.craft_master.model.Credential;
import com.gb.agile.craft_master.model.dto.CredentialDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CredentialServiceImplTest {

    @Autowired
    private CredentialService credentialService;

    @Test
    public void whenGetAllCredentials_thenFindShouldBeSuccessful() {
        List<Credential> list = credentialService.getAllCredentialByUserId(2);
        assertNotNull(list);
    }

    @Test
    public void whenAddCredentials_thenCountShouldBeIncrease() {
        int count = credentialService.getAllCredentialByUserId(2).size();
        credentialService.addCredential(2, new CredentialDto("mail", "a@a.a", "e-mail"));
        assertEquals(++count,  credentialService.getAllCredentialByUserId(2).size());
    }

    @Test
    public void whenDelCredentials_thenCountShouldBeIncrease() {
        List<Credential> list = credentialService.getAllCredentialByUserId(2);
        int count = list.size();
        credentialService.deleteCredential(2,list.get(0).getCode());
        assertEquals(--count,  credentialService.getAllCredentialByUserId(2).size());
    }
}