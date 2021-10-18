package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.services.interfaces.OccupationService;
import com.gb.agile.craft_master.model.dtos.OccupationDto;
import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.repositories.OccupationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OccupationServiceImpl implements OccupationService {

    private final OccupationRepository occupationRepository;
    private final ModelMapper modelMapper;

    @Override
    public Occupation getOccupationById(Long id) {
        return occupationRepository.getById(id);
    }

    @Override
    public OccupationDto getOccupationDtoById(Long id) {
        return modelMapper.map(occupationRepository.getById(id), OccupationDto.class);
    }

    @Override
    public List<OccupationDto> getOccupationDtosByParent(Long parentId) {
        return occupationRepository.getAllByParentId(parentId)
                .stream()
                .map(occupation -> (modelMapper.map(occupation, OccupationDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public List<OccupationDto> getAllOccupations() {
        return occupationRepository
                .findAll()
                .stream().map(occupation -> (modelMapper.map(occupation, OccupationDto.class)))
                .collect(Collectors.toList());
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
