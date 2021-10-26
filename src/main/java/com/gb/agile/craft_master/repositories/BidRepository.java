package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.dtos.BidDto;
import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> getByOfferId(Long offerId);

    @Query(value = "select b from Bid b where b.offer in (select o from Offer o where o.creator = ?1)")
    List<Bid> getUserOfferBids(User user);

}
