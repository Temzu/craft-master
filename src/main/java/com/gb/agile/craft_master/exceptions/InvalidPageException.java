package com.gb.agile.craft_master.exceptions;

public class InvalidPageException extends RuntimeException{
    public InvalidPageException(String msg) {
        super("Invalid page:" + msg);
    }
}
