package com.gb.agile.craft_master.exceptions.entityexceptions;

import com.gb.agile.craft_master.exceptions.CraftMasterError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class EntityExceptionControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<?> handleEntityBadIdException(EntityBadIdException e) {
    log.error(e.getMessage());
    CraftMasterError err = new CraftMasterError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
    log.error(e.getMessage());
    CraftMasterError err = new CraftMasterError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
  }
}
