package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> getByOfferId(Long offerId);
}
