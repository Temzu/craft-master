package com.gb.agile.craft_master.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.core.interfaces.OfferService;
import java.util.List;

import com.gb.agile.craft_master.repositories.ServiceRepository;
import com.gb.agile.craft_master.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@SpringBootTest
class OfferServiceImplTest {

  @Autowired
  private OfferService offerService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ServiceRepository serviceRepository;


  @Test
  void getAllOffers() {
    List<Offer> allOffers = offerService.getAllOffers();
    assertFalse(allOffers.isEmpty());
  }

  @CsvSource(value = {"1", "4"})
  @ParameterizedTest
  void getOfferById_true(Long id) {
    Offer offer = offerService.getOfferById(id);
    System.out.println(offer);
    assertNotNull(offer);
  }

  @CsvSource(value = {"0", "-1"})
  @ParameterizedTest
  void getOfferById_false_bad_id(Long id) {
    assertThrows(EntityBadIdException.class, () -> offerService.getOfferById(id));
  }

  @CsvSource(value = {"1000", "10000", "1000000"})
  @ParameterizedTest
  void getOfferById_false_not_found(Long id) {
    assertThrows(EntityNotFoundException.class, () -> offerService.getOfferById(id));
  }

  @CsvSource(value = {"1", "4"})
  @ParameterizedTest
  void deleteOfferById_true(Long id) {
    offerService.deleteOfferById(id);
  }

  @CsvSource(value = {"1000", "10000", "1000000"})
  @ParameterizedTest
  void deleteOfferById_false_not_found(Long id) {
    assertThrows(EntityNotFoundException.class, () -> offerService.deleteOfferById(id));
  }

  @CsvSource(value = {"0", "-1"})
  @ParameterizedTest
  void deleteOfferById_false_bad_id(Long id) {
    assertThrows(EntityBadIdException.class, () -> offerService.deleteOfferById(id));
  }

  @CsvSource(value = {"1", "5", "-1", "100"})
  @NullSource
  @ParameterizedTest
  void saveOrUpdate_true(Long id) {
    Offer offer = new Offer();
    offer.setId(id);
    offer.setTitle("Offer-123");
    offer.setUser(userRepository.getById(1));
    offer.setService(serviceRepository.getById(1L));

    offerService.saveOrUpdate(offer);
  }

  @Test
  void saveOrUpdate_false() {
    assertThrows(InvalidDataAccessApiUsageException.class, () -> offerService.saveOrUpdate(null));
  }
}