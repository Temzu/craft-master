package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.OccupationService;
import com.gb.agile.craft_master.model.dtos.OccupationDto;
import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.repositories.OccupationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OccupationServiceImpl implements OccupationService {

    private final OccupationRepository occupationRepository;

    @Override
    public Occupation getOccupationById(Integer id) {
        return occupationRepository.getById(Long.valueOf(id));
    }

    @Override
    public List<OccupationDto> getAllOccupations() {
        //ToDo: закешировать результат
        List<Occupation> occupations = occupationRepository.findAll();
        HashMap<Integer, OccupationDto> mapOccupationDto = new HashMap<>();
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

    @Override
    public void deleteOccupationById(Integer id) {

    }

    @Override
    public Occupation saveOrUpdate(Occupation offer) {
        return null;
    }
}
