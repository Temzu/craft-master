package com.gb.agile.craft_master.model.entities;

import javax.persistence.*;

import com.gb.agile.craft_master.model.User;
import lombok.Data;

@Data
@Entity
@Table(name = "offer")
public class Offer {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "service_id")
  private Service service;
}
