package com.yuji.localfood.builders;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

import com.yuji.localfood.domain.entities.Place;
import com.yuji.localfood.domain.enums.PlaceCategory;
import com.yuji.localfood.domain.enums.PriceLevel;
import com.yuji.localfood.domain.valueobjects.GeoPoint;
import com.yuji.localfood.domain.valueobjects.PhoneNumber;

public class PlaceTestDataBuilder {
  private static final Random rnd = new Random(42);

  private UUID id = UUID.randomUUID();
  private final String name = "Place-" + rnd.nextInt(10_000);
  private final String description = "Description-" + rnd.nextInt(10_000);
  private final String city = "City-" + rnd.nextInt(10_000);
  private final String country = "Country-" + rnd.nextInt(10_000);
  private final GeoPoint location = new GeoPoint(0, 0);
  private final PlaceCategory category = PlaceCategory.MARKET;
  private final PriceLevel priceLevel = PriceLevel.MODERATE;
  private final boolean localOwned = true;
  private final PhoneNumber phone = PhoneNumber.of("1234567890");
  private final String email = "email-" + rnd.nextInt(10_000) + "@mail.com";
  private final UUID createdBy = UUID.randomUUID();
  private final Instant createdAt = Instant.now();
  private final Instant updatedAt = Instant.now();

  public static PlaceTestDataBuilder aPlace() {
    return new PlaceTestDataBuilder();
  }

  public PlaceTestDataBuilder withId(UUID id) {
    this.id = id;
    return this;
  }

  public Place build() {
    return new Place(id, name, description, city, country, location, category, priceLevel,
        localOwned, phone, email, createdBy, createdAt, updatedAt);
  }
}
