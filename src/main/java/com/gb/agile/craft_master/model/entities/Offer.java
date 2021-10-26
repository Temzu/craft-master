package com.gb.agile.craft_master.model.entities;

import com.gb.agile.craft_master.core.enums.OfferStatus;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.dtos.SaveOfferDto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
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

  @Column(name = "price")
  private BigDecimal price;

  @Basic
  @Column(name = "offer_status", columnDefinition = "integer default 1")
  private Integer offerStatusValue;

  @ManyToOne
  @JoinColumn(name = "user_creator_id")
  private User creator;

  @ManyToOne
  @JoinColumn(name = "occupation_id")
  private Occupation occupation;

  @ManyToOne
  @JoinColumn(name = "accepted_bid_id")
  private Bid acceptedBid;

  @ManyToOne
  @JoinColumn(name = "user_executor_id")
  private User executor;

  @Column(name = "created_at")
  @CreationTimestamp
  private ZonedDateTime createdAt;

  @Transient
  private OfferStatus offerStatus;

  @PostLoad
  void fillTransient() {
    if (offerStatusValue > 0) {
      this.offerStatus = OfferStatus.of(offerStatusValue);
    }
  }

  @PrePersist
  void fillPersistent() {
    if (offerStatus != null) {
      this.offerStatusValue = offerStatus.getCode();
    }
  }

  public Offer(OfferDto offerDto) {
    this.title = offerDto.getTitle();
    this.description = offerDto.getDescription();
    this.offerStatusValue = OfferStatus.CREATED.getCode();
  }
}
