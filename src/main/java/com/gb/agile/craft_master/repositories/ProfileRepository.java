package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.dtos.ProfileDto;
import com.gb.agile.craft_master.model.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> getAllByUserId(Long userId);

    void deleteById (Long Id);

}
