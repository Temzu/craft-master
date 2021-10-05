package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.services.OccupationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/occupations")
@RequiredArgsConstructor
public class OccupationController {

    private final OccupationService occupationService;

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
