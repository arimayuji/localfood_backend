package com.yuji.localfood.domain.entities;

import java.time.Instant;
import java.util.UUID;

import com.yuji.localfood.domain.enums.PlaceCategory;
import com.yuji.localfood.domain.enums.PriceLevel;
import com.yuji.localfood.domain.validations.Preconditions;
import com.yuji.localfood.domain.valueobjects.GeoPoint;
import com.yuji.localfood.domain.valueobjects.PhoneNumber;

public final class Place {
  private final UUID id;
  private final String name;
  private final String description;
  private final String city;
  private final String country;
  private final GeoPoint location;
  private final PlaceCategory category;
  private final PriceLevel priceLevel;
  private final boolean localOwned;
  private final PhoneNumber phone;
  private final String email;
  private final UUID createdBy;
  private final Instant createdAt;
  private final Instant updatedAt;

  public Place(UUID id, String name, String description, String city, String country,
      GeoPoint location, PlaceCategory category, PriceLevel priceLevel,
      boolean localOwned, PhoneNumber phone, String email, UUID createdBy,
      Instant createdAt, Instant updatedAt) {
    this.id = Preconditions.requireNonNullArg(id, "id");
    this.name = Preconditions.requireNonNullArg(name, "name").trim();
    this.description = description;
    this.city = Preconditions.requireNonNullArg(city, "city").trim();
    this.country = Preconditions.requireNonNullArg(country, "country").trim().toUpperCase();
    if (this.country.length() != 2)
      throw new IllegalArgumentException("country must be ISO-3166 alpha-2");
    this.location = Preconditions.requireNonNullArg(location, "location");
    this.category = Preconditions.requireNonNullArg(category, "category");
    this.priceLevel = Preconditions.requireNonNullArg(priceLevel, PriceLevel.MODERATE.toString());
    this.localOwned = localOwned;
    this.phone = phone;
    this.email = email;
    this.createdBy = Preconditions.requireNonNullArg(createdBy, "createdBy");
    this.createdAt = Preconditions.requireNonNullArg(createdAt, "createdAt");
    this.updatedAt = Preconditions.requireNonNullArg(updatedAt, "updatedAt");
  }

  public static Place createNew(String name, String description, String city, String country,
      GeoPoint location, PlaceCategory category, PriceLevel priceLevel,
      boolean localOwned, PhoneNumber phone, String email, UUID createdBy) {
    var now = Instant.now();
    return new Place(UUID.randomUUID(), name, description, city, country, location,
        category, priceLevel, localOwned, phone, email, createdBy, now, now);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getCity() {
    return city;
  }
 
  public String getCountry() {
    return country;
  }

  public GeoPoint getLocation() {
    return location;
  }

  public PlaceCategory getCategory() {
    return category;
  }

  public PriceLevel getPriceLevel() {
    return priceLevel;
  }

  public boolean isLocalOwned() {
    return localOwned;
  }

  public PhoneNumber getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public UUID getCreatedBy() {
    return createdBy;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public Place withUpdatedAt(Instant newUpdatedAt) {
    return new Place(id, name, description, city, country, location, category, priceLevel,
        localOwned, phone, email, createdBy, createdAt, newUpdatedAt);
  }

  @Override
  public String toString() {
    return "Place{id=%s, name=%s, city=%s, country=%s, category=%s, priceLevel=%s, localOwned=%s, createdBy=%s}"
        .formatted(id, name, city, country, category, priceLevel, localOwned, createdBy);
  }
}
