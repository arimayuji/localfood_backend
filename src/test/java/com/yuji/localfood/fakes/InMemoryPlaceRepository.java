package com.yuji.localfood.fakes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.yuji.localfood.domain.entities.Place;
import com.yuji.localfood.domain.enums.PlaceCategory;
import com.yuji.localfood.domain.repositories.PlaceRepository;
import com.yuji.localfood.domain.valueobjects.GeoPoint;

public class InMemoryPlaceRepository implements PlaceRepository {
  private final Map<UUID, Place> places = new HashMap<>();
  private final Map<UUID, UUID> userIds = new HashMap<>();

  @Override
  public Place create(Place place) {
    final UUID placeId = place.getId();
    final UUID ownerId = place.getCreatedBy();

    if (places.containsKey(placeId)) {
      throw new IllegalArgumentException("Place already exists" + placeId);
    }

    if (!userIds.containsKey(ownerId)) {
      throw new IllegalArgumentException("User not found" + ownerId);
    }

    places.put(placeId, place);
    userIds.put(ownerId, placeId);
    return place;
  }

  @Override
  public Place update(Place newPlace) {
    final UUID placeId = newPlace.getId();
    final UUID ownerId = newPlace.getCreatedBy();

    if (!places.containsKey(placeId)) {
      throw new IllegalArgumentException("Place not found" + placeId);
    }

    if (!userIds.containsKey(ownerId)) {
      throw new IllegalArgumentException("User not found" + ownerId);
    }

    places.put(placeId, newPlace);
    userIds.put(ownerId, placeId);
    return newPlace;
  }

  @Override
  public Optional<Place> findById(UUID id) {
    return Optional.ofNullable(places.get(id));
  }

  @Override
  public Optional<List<Place>> findByCity(String city) {
    final List<Place> placesInCity = places.values().stream().filter(p -> p.getCity().equals(city)).toList();

    return Optional.ofNullable(placesInCity);
  }

  @Override
  public Optional<List<Place>> findByCountry(String country) {
    final List<Place> placesInCountry = places.values().stream().filter(p -> p.getCountry().equals(country)).toList();

    return Optional.ofNullable(placesInCountry);
  }

  @Override
  public Optional<List<Place>> findNearest(GeoPoint location) {
    throw new UnsupportedOperationException("Unimplemented method 'findNearest'");
  }

  @Override
  public Optional<List<Place>> findByCategory(PlaceCategory category) {
    final List<Place> placesInCategory = places.values().stream().filter(p -> p.getCategory().equals(category))
        .toList();

    return Optional.ofNullable(placesInCategory);
  }

  @Override
  public Optional<List<Place>> findAll() {
    return Optional.ofNullable(places.values().stream().toList());
  }

  @Override
  public Optional<List<Place>> findByOwnerId(UUID ownerId) {
    if (!userIds.containsKey(ownerId)) {
      throw new IllegalArgumentException("User not found" + ownerId);
    }

    final List<Place> placesByOwnerId = places.values().stream().filter(p -> p.getCreatedBy().equals(ownerId)).toList();

    return Optional.ofNullable(placesByOwnerId);
  }

  @Override
  public Optional<List<Place>> findByCityAndCountry(String city, String country) {
    final List<Place> placesInCityAndCountry = places.values().stream()
        .filter(p -> p.getCity().equals(city) && p.getCountry().equals(country)).toList();

    return Optional.ofNullable(placesInCityAndCountry);
  }

  @Override
  public Optional<List<Place>> findByCategoryAndCountry(PlaceCategory category, String country) {
    final List<Place> placesInCategoryAndCountry = places.values().stream().filter(
        p -> p.getCategory().equals(category) && p.getCountry().equals(country)).toList();

    return Optional.ofNullable(placesInCategoryAndCountry);
  }

  @Override
  public Optional<List<Place>> findByCategoryAndCity(PlaceCategory category, String city) {
    final List<Place> placesInCategoryAndCity = places.values().stream().filter(
        p -> p.getCategory().equals(category) && p.getCity().equals(city)).toList();

    return Optional.ofNullable(placesInCategoryAndCity);
  }

  @Override
  public Optional<List<Place>> findByCategoryAndCityAndCountry(PlaceCategory category, String city, String country) {
    final List<Place> placesInCategoryAndCityAndCountry = places.values().stream().filter(
        p -> p.getCategory().equals(category) && p.getCity().equals(city) && p.getCountry().equals(country)).toList();

    return Optional.ofNullable(placesInCategoryAndCityAndCountry);
  }

  @Override
  public void delete(UUID id) {
    if (!places.containsKey(id)) {
      throw new IllegalArgumentException("Place not found" + id);
    }

    places.remove(id);
    userIds.remove(places.get(id).getCreatedBy());
  }

}
