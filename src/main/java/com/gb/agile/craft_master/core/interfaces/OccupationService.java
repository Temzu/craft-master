package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.dtos.OccupationDto;
import com.gb.agile.craft_master.model.entities.Occupation;

import java.util.List;

public interface OccupationService {

    List<OccupationDto> getAllOccupations();

    Occupation getOccupationById(Integer id);

    void deleteOccupationById(Integer id);

    Occupation saveOrUpdate(Occupation offer);
}
