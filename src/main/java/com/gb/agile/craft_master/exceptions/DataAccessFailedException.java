package com.gb.agile.craft_master.exceptions;

public class DataAccessFailedException extends RuntimeException {

  private static final String CANNOT_ACCESS = "You cannot access this method for: %s";

  public DataAccessFailedException(Class<?> entityClass) {
    super(String.format(CANNOT_ACCESS, entityClass.getSimpleName()));
  }
}
