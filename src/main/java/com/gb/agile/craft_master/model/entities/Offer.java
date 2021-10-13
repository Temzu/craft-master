package com.gb.agile.craft_master.model.entities;

import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

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

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id")
  private User user;

  // вид работ/услуг
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "occupation_id")
  private Occupation occupation;

  // выбранное предложение
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "bid_id")
  private Bid bid;

  // цена заявки
  @Column (name = "price")
  private BigDecimal price;

  // дата ввода заявки
  @Column (name = "date_beg")
  private ZonedDateTime dateBeg;

  // дата завершения заявки (время жизни)
  @Column (name = "date_end")
  private ZonedDateTime dateEnd;

}
