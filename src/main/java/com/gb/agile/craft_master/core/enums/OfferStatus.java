package com.gb.agile.craft_master.core.enums;

import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import java.util.Arrays;

public enum OfferStatus {

  CREATED(1),
  ASSIGNED(2),
  DONE(3),
  CLOSED(4);

  private final int code;

  OfferStatus(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static OfferStatus of(int code) {
    return Arrays.stream(values()).filter(offerStatus -> offerStatus.code == code).findFirst()
        .orElseThrow(() -> new EntityNotFoundException(OfferStatus.class, (long) code));
  }

  public static boolean contains(int code) {
    return Arrays.stream(values()).anyMatch(offerStatus -> offerStatus.code == code);
  }

  @Override
  public String toString() {
    return this.name();
  }
}
