package com.gb.agile.craft_master.exceptions;

public class LoginExistsException extends RuntimeException {

    public LoginExistsException(String message) {
        super(message);
    }
}
