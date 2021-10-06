package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.OccupationService;
import com.gb.agile.craft_master.model.dtos.OccupationDto;
import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.repositories.OccupationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OccupationServiceImpl implements OccupationService {

    private final OccupationRepository occupationRepository;

    @Override
    public Occupation getOccupationById(Long id) {
        return occupationRepository.getById(id);
    }

    @Override
    public List<OccupationDto> getOccupationsByParent(Long parentId) {
        return occupationRepository.getAllByParentId(parentId)
                .stream()
                .map(OccupationDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<OccupationDto> getAllOccupations() {
        //ToDo: закешировать результат
        List<Occupation> occupations = occupationRepository.findAll();
        HashMap<Long, OccupationDto> mapOccupationDto = new HashMap<>();
        List<OccupationDto> resList = new ArrayList<>();

        for (Occupation occupation : occupations) {
            OccupationDto occupationDto;
            if (mapOccupationDto.containsKey(occupation.getId())) {
                occupationDto = mapOccupationDto.get(occupation.getId());
                occupationDto.setName(occupation.getName());
            } else {
                occupationDto = new OccupationDto(occupation);
                mapOccupationDto.put(occupation.getId(), occupationDto);
            }
            if (occupation.getParentId() == null) {
                resList.add(occupationDto);
            } else {
                OccupationDto parent;
                if (mapOccupationDto.containsKey(occupation.getParentId())) {
                    parent = mapOccupationDto.get(occupation.getParentId());
                } else {
                    parent = new OccupationDto(occupation.getParentId());
                    mapOccupationDto.put(parent.getId(), parent);
                }
                parent.addChild(occupationDto);
            }
        }
        return resList;
    }

    @Transactional
    @Override
    public void deleteOccupationById(Long id) {
        occupationRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Occupation saveOrUpdate(Occupation occupation) {
        return occupationRepository.save(occupation);
    }
}
