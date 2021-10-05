package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.OccupationService;
import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.repositories.OccupationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class OccupationServiceImpl implements OccupationService {

    private OccupationRepository occupationRepository;

    public List<Occupation> getOccupations () {
        return occupationRepository.findAll();
    }

    public List<Occupation> getOccupationsByParent (Long parentId) {
        return occupationRepository.getAllByParentId(parentId);
    }

    public Occupation getOccupation(Long id) {
        return occupationRepository.getById(id);
    }

    @Transactional
    public boolean deleteOccupation(Long id) {
        return occupationRepository.deleteById(id);
    }

    @Transactional
    public Occupation saveOccupation(Occupation occupation) {
        return occupationRepository.save(occupation);
    }

}
