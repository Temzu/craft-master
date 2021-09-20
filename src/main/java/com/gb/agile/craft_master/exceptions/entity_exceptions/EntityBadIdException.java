package com.gb.agile.craft_master.exceptions.entity_exceptions;

public class EntityBadIdException extends RuntimeException {

  private static final String BAD_ID = "%s: id %d should be > 0";

  public EntityBadIdException(Class<?> entityClass, Long id) {
    super(String.format(BAD_ID, entityClass.getSimpleName(), id));
  }
}
