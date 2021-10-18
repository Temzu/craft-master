package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.services.OccupationServiceImpl;
import com.gb.agile.craft_master.model.dtos.OccupationDto;
import com.gb.agile.craft_master.model.entities.Occupation;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/occupations")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class OccupationController {

private final OccupationServiceImpl occupationService;

    @GetMapping
    public List<OccupationDto> getAllOccupations() {
        return occupationService.getAllOccupations();
    }

    @GetMapping("/{id}")
    public OccupationDto getOccupation(@PathVariable Long id) {
        return occupationService.getOccupationDtoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOccupation(@PathVariable Long id) {
        occupationService.deleteOccupationById(id);
    }

    @PostMapping
    public Occupation saveOccupation(@RequestBody Occupation occupation) {
        return occupationService.saveOrUpdate(occupation);
    }

    @PutMapping
    public Occupation updateOccupation(@RequestBody Occupation occupation) {
        return occupationService.saveOrUpdate(occupation);
    }

}
