package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.entities.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Integer> {

    Occupation getById (Long id);

    List<Occupation> getAllByParentId(Long parentId);

    boolean deleteById(Long id);

}
