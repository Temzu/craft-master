package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.entities.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccupationRepository extends JpaRepository<Occupation, Long> {

    List<Occupation> getAllByParentId(Long parentId);

    boolean deleteOccupationById(Long id);
}
