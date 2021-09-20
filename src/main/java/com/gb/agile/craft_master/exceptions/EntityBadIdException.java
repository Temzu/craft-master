package com.gb.agile.craft_master.exceptions;

public class EntityBadIdException extends RuntimeException {

  private static final String BAD_ID = "%s with id %d should be > 0";

  public EntityBadIdException(Class<?> entityClass, Long id) {
    super(String.format(BAD_ID, entityClass.getSimpleName(), id));
  }
}
