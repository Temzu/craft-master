package com.gb.agile.craft_master.model;

import com.gb.agile.craft_master.model.dto.CredentialDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "credential")
@Getter
@Setter
@NoArgsConstructor
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column
    private String code;

    @Column
    private String value;

    @Column
    private String name;

    public Credential(Integer userId, CredentialDto credential) {
        this.userId = userId;
        this.code = credential.getCode();
        this.value = credential.getValue();
        this.name = credential.getName();
    }
}