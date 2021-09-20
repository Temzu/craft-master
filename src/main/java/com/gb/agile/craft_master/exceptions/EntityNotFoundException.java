package com.gb.agile.craft_master.exceptions;

public class EntityNotFoundException extends RuntimeException {

  private static final String NOT_FOUND_BY_ID = "%s with id %d not found";
  private static final String NOT_FOUND_BY_NAME = "%s with name %s not found";

  public EntityNotFoundException(Class<?> entityClass, Long id) {
    super(String.format(NOT_FOUND_BY_ID, entityClass.getSimpleName(), id));
  }

  public EntityNotFoundException(Class<?> entityClass, String name) {
    super(String.format(NOT_FOUND_BY_NAME, entityClass.getSimpleName(), name));
  }
}
