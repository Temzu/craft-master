package com.gb.agile.craft_master.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "occupation")
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    private String name;

    @Override
    public String toString() {
        return String.format("%s [%d/%d]", name, id, parentId);
    }

}
