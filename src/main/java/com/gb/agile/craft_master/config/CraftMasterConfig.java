package com.gb.agile.craft_master.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CraftMasterConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
