package com.gb.agile.craft_master.model.entities;

import com.gb.agile.craft_master.model.dtos.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Credential> credentials;

    public User(UserDto user) {
        this.login = user.getLogin();
        this.name = user.getName();
        this.role = user.getRole();
        this.password = user.getPassword();
    }
}