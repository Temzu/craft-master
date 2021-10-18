package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.model.dtos.OccupationDto;
import com.gb.agile.craft_master.model.entities.Occupation;

import java.util.List;

public interface OccupationService {

    List<OccupationDto> getAllOccupations();

    OccupationDto getOccupationDtoById(Long id);

    Occupation getOccupationById(Long id);

    List<OccupationDto> getOccupationDtosByParent(Long parentId);

    void deleteOccupationById(Long id);

    Occupation saveOrUpdate(Occupation offer);
}
