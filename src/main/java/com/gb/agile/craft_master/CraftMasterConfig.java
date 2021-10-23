package com.gb.agile.craft_master;

import com.gb.agile.craft_master.model.dtos.*;
import com.gb.agile.craft_master.model.entities.Offer;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Convert;

@Configuration
public class CraftMasterConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}
