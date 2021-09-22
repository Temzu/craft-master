package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.Credential;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialDto {
    private String code;
    private String value;
    private String name;

    public CredentialDto(Credential credential) {
        this.code = credential.getCode();
        this.value = credential.getValue();
        this.name = credential.getName();
    }
}
