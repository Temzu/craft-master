package com.gb.agile.craft_master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CraftMasterApplication {

  public static void main(String[] args) {
    SpringApplication.run(CraftMasterApplication.class, args);
  }

}
