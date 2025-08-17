package com.yuji.localfood.contracts;

import java.util.UUID;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.yuji.localfood.builders.PlaceTestDataBuilder;
import com.yuji.localfood.domain.repositories.PlaceRepository;

public abstract class PlaceRepositoryContractTest {

  protected abstract PlaceRepository repo();

  @Test
  void createThenFindById() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();

    repo().create(place);
    var found = repo().findById(place.getId());

    assertTrue(found.isPresent());
    assertEquals(place, found.get());
  }

  @Test
  void updateRequiresExistingPlace() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();
    Exception placeNotFoundException = assertThrows(IllegalArgumentException.class, () -> repo().update(place));

    assertEquals("place", placeNotFoundException.getMessage());

    repo().create(place);

    var updated = PlaceTestDataBuilder.aPlace().withId(place.getId()).build();
    var saved = repo().update(updated);

    assertEquals(updated.getName(), saved.getName());
    assertEquals(updated.getName(), repo().findById(place.getId()).get().getName());
  }

  @Test
  void deleteById() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();

    repo().create(place);
    repo().delete(place.getId());
    assertTrue(repo().findById(place.getId()).isEmpty());
  }

  @Test
  void createPlaceAndFindByCity() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();
    repo().create(place);

    var found = repo().findByCity(place.getCity());
    assertTrue(found.isPresent());

    var places = found.get();

    assertEquals(place, places.get(0));
  }

  @Test
  void createPlaceAndFindByCountry() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();
    repo().create(place);

    var found = repo().findByCountry(place.getCountry());
    assertTrue(found.isPresent());

    var placs = found.get();

    assertEquals(place, placs.get(0));
  }

  @Test
  void createPlaceAndFindByCategory() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();
    repo().create(place);

    var found = repo().findByCategory(place.getCategory());
    assertTrue(found.isPresent());

    var places = found.get();
    assertEquals(place, places.get(0));
  }

  @Test
  void createPlaceAndFindByOwnerId() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();
    repo().create(place);

    var found = repo().findByOwnerId(place.getCreatedBy());
    assertTrue(found.isPresent());

    var places = found.get();
    assertEquals(place, places.get(0));
  }

  @Test
  void createPlaceAndFindByCityAndCategory() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();
    repo().create(place);

    var found = repo().findByCategoryAndCity(place.getCategory(), place.getCity());
    assertTrue(found.isPresent());

    var places = found.get();
    assertEquals(place, places.get(0));
  }

  @Test
  void createPlaceAndFindByCategoryAndCountry() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();
    repo().create(place);

    var found = repo().findByCategoryAndCountry(place.getCategory(), place.getCountry());
    assertTrue(found.isPresent());

    var places = found.get();
    assertEquals(place, places.get(0));
  }

  @Test
  void createPlaceAndFindByCityAndCountry() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();
    repo().create(place);

    var found = repo().findByCityAndCountry(place.getCity(), place.getCountry());
    assertTrue(found.isPresent());

    var places = found.get();
    assertEquals(place, places.get(0));
  }

  @Test
  void createPlaceAndFindByCityAndCountryAndCategory() {
    var place = PlaceTestDataBuilder.aPlace().withId(UUID.randomUUID()).build();
    repo().create(place);

    var found = repo().findByCategoryAndCityAndCountry(place.getCategory(), place.getCity(), place.getCountry());
    assertTrue(found.isPresent());

    var places = found.get();
    assertEquals(place, places.get(0));
  }
}
