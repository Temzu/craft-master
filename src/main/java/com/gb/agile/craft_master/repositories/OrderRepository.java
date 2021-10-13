package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Bid, Long> {

}
