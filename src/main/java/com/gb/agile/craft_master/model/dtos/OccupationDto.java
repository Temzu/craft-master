package com.gb.agile.craft_master.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class OccupationDto {
  
    private Long id;
    private String name;
    private List<OccupationDto> children;

}
