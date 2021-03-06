package com.gb.agile.craft_master.exceptions.handlers;

import com.gb.agile.craft_master.exceptions.CraftMasterError;
import com.gb.agile.craft_master.exceptions.DataAccessFailedException;
import com.gb.agile.craft_master.exceptions.LoginFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AuthExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleLoginFailedException(LoginFailedException e) {
        log.error(e.getMessage());
        CraftMasterError err = new CraftMasterError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleDataAccessFailedException(DataAccessFailedException e) {
        log.error(e.getMessage());
        CraftMasterError err = new CraftMasterError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }
}
