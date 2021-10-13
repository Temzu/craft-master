package com.gb.agile.craft_master.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// профиль пользователя
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "profile")
public class Profile {

    @Id
    @Column(name = "id")
    @Setter(value = AccessLevel.PRIVATE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "occupation_id")
    private Long occupationId;

}
