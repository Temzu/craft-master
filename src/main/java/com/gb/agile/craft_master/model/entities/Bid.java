package com.gb.agile.craft_master.model.entities;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "bid")
public class Bid {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(value = AccessLevel.PRIVATE)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id")
  private User user;

  @Column (name = "offer_id")
  private Long offerId;

  @Column (name = "price")
  private BigDecimal price;

  // дата ввода заявки
  @Column (name = "date_beg")
  private ZonedDateTime dateBeg;

  // дата завершения заявки (время жизни)
  @Column (name = "date_end")
  private ZonedDateTime dateEnd;

}
