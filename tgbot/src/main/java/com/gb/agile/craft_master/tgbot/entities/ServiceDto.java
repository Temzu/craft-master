package com.gb.agile.craft_master.tgbot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDto implements Serializable {
    private Integer id;
    private String name;
    private List<ServiceDto> child;
}
