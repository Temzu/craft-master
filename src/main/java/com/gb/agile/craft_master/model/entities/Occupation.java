package com.gb.agile.craft_master.model.entities;

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
  @Column(name = "id")
  @Setter(value = AccessLevel.PRIVATE)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "parentId")
  private Long parentId;

  @Column(name = "name")
  private String name;

  @Override
  public String toString() {
    return String.format("%s [%d/%d]", name, id, parentId);
  }


}
