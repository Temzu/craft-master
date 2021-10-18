package com.gb.agile.craft_master.model.entities;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "occupation")
public class Occupation {

  @Id
  @Column(name = "id")
  @Setter(value = AccessLevel.PRIVATE)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional=true)
  @JoinColumn(name="parent_id")
  private Occupation parent;

  @OneToMany(mappedBy="parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
  private List<Occupation> children = new ArrayList<>();

  @Override
  public String toString() {
    return String.format("%s [%d/%d]", name, id, parent.getId());
  }

}
