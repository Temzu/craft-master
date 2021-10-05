package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.model.Occupation;
import com.gb.agile.craft_master.repositories.OccupationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class OccupationService {

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
