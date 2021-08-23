package com.gb.agile.craft_master.exceptions;

public class OfferException extends RuntimeException {

  private static final String OFFER_NOT_FOUND = "Offer: with id %d not found";

  public OfferException(String message) {
    super(message);
  }

  public static OfferException offerNotFound(Long id) {
    return new OfferException(String.format(OFFER_NOT_FOUND, id));
  }
}
