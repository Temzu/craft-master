package com.gb.agile.craft_master.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class CraftMasterError {
    private int status;
    private String message;
    private Date timestamp;

    public CraftMasterError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
