package com.gb.agile.craft_master.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "service")
public class Service {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "parentId")
  private Integer parentId;

  @Column(name = "name")
  private String name;
}
