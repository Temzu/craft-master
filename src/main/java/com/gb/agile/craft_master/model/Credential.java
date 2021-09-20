package com.gb.agile.craft_master.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "credential")
@Getter
@Setter
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String user_id;

    @Column
    private String code;

    @Column
    private String value;

    @Column
    private String name;
}