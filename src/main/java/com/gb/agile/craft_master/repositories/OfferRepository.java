package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.model.entities.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>,
    JpaSpecificationExecutor<Offer> {

  Page<Offer> findAllByOfferStatusValueAndCreatorIsNotAndOccupationIdIn(Integer statusCode,
      User creator,
      List<Long> occupations,
      Pageable pageable);

  boolean existsByIdAndCreator(Long id, User creator);

  List<Offer> findAllByCreatorId(Long creatorId, Pageable pageable);
}
