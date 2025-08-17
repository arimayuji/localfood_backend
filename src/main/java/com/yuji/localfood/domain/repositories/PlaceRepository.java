package com.yuji.localfood.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.yuji.localfood.domain.entities.Place;
import com.yuji.localfood.domain.enums.PlaceCategory;
import com.yuji.localfood.domain.valueobjects.GeoPoint;

public interface PlaceRepository {
  Place create(Place place);

  Place update(Place newPlace);

  Optional<Place> findById(UUID id);

  Optional<List<Place>> findByCity(String city);

  Optional<List<Place>> findByCountry(String country);

  Optional<List<Place>> findNearest(GeoPoint location);

  Optional<List<Place>> findByCategory(PlaceCategory category);

  Optional<List<Place>> findAll();

  Optional<List<Place>> findByOwnerId(UUID ownerId);

  Optional<List<Place>> findByCityAndCountry(String city, String country);

  Optional<List<Place>> findByCategoryAndCountry(PlaceCategory category, String country);

  Optional<List<Place>> findByCategoryAndCity(PlaceCategory category, String city);

  Optional<List<Place>> findByCategoryAndCityAndCountry(PlaceCategory category, String city, String country);

  void delete(UUID id);
}
