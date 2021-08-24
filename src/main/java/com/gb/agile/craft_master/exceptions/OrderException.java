package com.gb.agile.craft_master.exceptions;

public class OrderException extends RuntimeException {

  private static final String ORDER_NOT_FOUND = "Order: with id %d not found";
  private static final String BAD_ORDER_ID = "id: %d, id should be greater than 0";

  public OrderException(String message) {
    super(message);
  }

  public static OrderException orderNotFound(Long id) {
    return new OrderException(String.format(ORDER_NOT_FOUND, id));
  }

  public static OrderException badOrderId(Long id) {
    return new OrderException(String.format(BAD_ORDER_ID, id));
  }
}
