package com.gb.agile.craft_master.repositories;

import com.gb.agile.craft_master.model.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> getAllByUserId(Long userId);

    void deleteById (Long Id);
    List<Profile> findAllByUserId(Long userId);

    @Modifying
    @Query(value = "delete from profile p where p.id in :listId and p.user_id = :userId", nativeQuery = true)
    int deleteByIds(@Param("listId") List<Long> ids, @Param("userId") Long userId);
}
