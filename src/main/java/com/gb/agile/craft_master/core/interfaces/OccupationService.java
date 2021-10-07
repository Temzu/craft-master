package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.dtos.OccupationDto;
import com.gb.agile.craft_master.model.entities.Occupation;

import java.util.List;

public interface OccupationService {

    List<OccupationDto> getAllOccupations();

    Occupation getOccupationById(Long id);

    List<OccupationDto> getOccupationsByParent(Long parentId);

    void deleteOccupationById(Long id);

    Occupation saveOrUpdate(Occupation offer);
}
