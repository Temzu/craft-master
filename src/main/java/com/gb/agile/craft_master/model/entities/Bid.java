package com.gb.agile.craft_master.model.entities;

import javax.persistence.*;

import com.gb.agile.craft_master.model.dtos.AddBidDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "bid")
@NoArgsConstructor
public class Bid {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(value = AccessLevel.PRIVATE)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn (name = "offer_id")
  private Offer offer;

  @Column (name = "price")
  private BigDecimal price;

  // дата ввода заявки
  @Column (name = "date_beg")
  private ZonedDateTime dateBeg;

  // дата завершения заявки (время жизни)
  @Column (name = "date_end")
  private ZonedDateTime dateEnd;

  @Column (name = "rating")
  private Float rating;

  public Bid(AddBidDto addBidDto) {
    this.price = addBidDto.getPrice();
    this.dateBeg = ZonedDateTime.ofInstant(Instant.ofEpochMilli(addBidDto.getDateBeg()), ZoneId.of("UTC"));
    this.dateEnd = ZonedDateTime.ofInstant(Instant.ofEpochMilli(addBidDto.getDateEnd()), ZoneId.of("UTC"));
  }
}
