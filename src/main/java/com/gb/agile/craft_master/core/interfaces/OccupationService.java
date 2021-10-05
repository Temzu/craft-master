package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.entities.Occupation;

import java.util.List;

public interface OccupationService {

    public List<Occupation> getOccupations ();

    public List<Occupation> getOccupationsByParent (Long parentId);

    public Occupation getOccupation(Long id);

    public boolean deleteOccupation(Long id);

    public Occupation saveOccupation(Occupation occupation);

}
