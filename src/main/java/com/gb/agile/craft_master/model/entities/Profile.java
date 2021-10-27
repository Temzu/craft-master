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

    @OneToOne
    @JoinColumn(name = "occupation_id", referencedColumnName = "id")
    private Occupation occupation;

    @Column(name = "work_exp")
    private Integer workExp;

    @Column(name = "description")
    private String description;

}
