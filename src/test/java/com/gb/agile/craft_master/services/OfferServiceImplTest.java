package com.gb.agile.craft_master.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gb.agile.craft_master.exceptions.OfferException;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.services.interfaces.OfferService;
import java.util.List;
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

  @Test
  void getAllOffers() {
    List<Offer> allOffers = offerService.getAllOffers();
    assertFalse(allOffers.isEmpty());
  }

  @CsvSource(value = {"1", "4"})
  @ParameterizedTest
  void getOfferById_true(Long id) {
    Offer offer = offerService.getOfferById(id);
    assertNotNull(offer);
  }

  @CsvSource(value = {"0", "-1", "1000"})
  @ParameterizedTest
  void getOfferById_false(Long id) {
    assertThrows(OfferException.class, () -> offerService.getOfferById(id));
  }

  @CsvSource(value = {"1", "4"})
  @ParameterizedTest
  void deleteOfferById_true(Long id) {
    offerService.deleteOfferById(id);
  }

  @CsvSource(value = {"0", "-1", "1000"})
  @ParameterizedTest
  void deleteOfferById_false(Long id) {
    assertThrows(OfferException.class, () -> offerService.deleteOfferById(id));
  }

  @CsvSource(value = {"1", "5", "-1", "100"})
  @NullSource
  @ParameterizedTest
  void saveOrUpdate_true(Long id) {
    Offer offer = new Offer();
    offer.setId(id);
    offer.setName("Offer-123");

    offerService.saveOrUpdate(offer);
  }

  @Test
  void saveOrUpdate_false() {
    assertThrows(InvalidDataAccessApiUsageException.class, () -> offerService.saveOrUpdate(null));
  }
}