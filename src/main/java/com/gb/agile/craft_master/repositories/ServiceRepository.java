package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
