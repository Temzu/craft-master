package com.gb.agile.craft_master.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gb.agile.craft_master.core.enums.OfferStatus;
import io.swagger.models.auth.In;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

  @Basic
  @Column(name = "offer_status")
  private Integer offerStatusValue;

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

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "occupation_id")
  private Occupation occupation;
}
