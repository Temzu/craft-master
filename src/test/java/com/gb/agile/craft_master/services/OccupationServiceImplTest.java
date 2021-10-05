package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.OccupationService;
import com.gb.agile.craft_master.model.entities.Occupation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OccupationServiceImplTest {

    public static final String PARENT_NAME = "Сервис-родитель";
    public static final String CHILD_NAME = "Сервис-потомок";

    @Autowired
    private OccupationServiceImpl occupationService;

    @Test
    public void setOccupationTest() {
        Occupation parent;
        Occupation occupation = new Occupation();
        occupation.setName(PARENT_NAME);
        parent = occupationService.saveOccupation(occupation);
        occupation = new Occupation();
        occupation.setName(CHILD_NAME);
        occupation.setParentId(parent.getId());
        occupation = occupationService.saveOccupation(occupation);
        assertEquals(occupation.getName(), CHILD_NAME);
    }

    @Test
    public void getOccupationTest() {
        Occupation occupation = occupationService.getOccupation(Long.valueOf(1));
        assertEquals(occupation.getId(), Long.valueOf(1));
    }

}
