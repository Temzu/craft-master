package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.services.OccupationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/occupations")
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class OccupationController {

    private final OccupationServiceImpl occupationService;

    @GetMapping()
    public List<Occupation> getAllOccupations() {
        return occupationService.getOccupations();
    }

    @GetMapping("/{id}")
    public Occupation getOccupation(@PathVariable Long id) {
        return occupationService.getOccupation(id);
    }

    @GetMapping("/del/{id}")
    public void deleteOccupation(@PathVariable Long id) {
        occupationService.deleteOccupation(id);
    }

    @PostMapping
    public Occupation saveOccupation(@RequestBody Occupation occupation) {
        return occupationService.saveOccupation(occupation);
    }

}
