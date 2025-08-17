package com.yuji.localfood.builders;

import java.time.Instant;
import java.util.UUID;

import com.yuji.localfood.domain.entities.FavoritePlace;

public class FavoritePlaceTestDataBuilder {
  public UUID userId = UUID.randomUUID();
  public UUID placeId = UUID.randomUUID();
  public Instant createdAt = Instant.now();
  public Instant updatedAt = Instant.now();

  public static FavoritePlaceTestDataBuilder aFavoritePlace() {
    return new FavoritePlaceTestDataBuilder();
  }

  public FavoritePlaceTestDataBuilder withUserId(UUID userId) {
    this.userId = userId;
    return this;
  }

  public FavoritePlaceTestDataBuilder withPlaceId(UUID placeId) {
    this.placeId = placeId;
    return this;
  }

  public FavoritePlace build() {
    return new FavoritePlace(userId, placeId, createdAt);
  }
}
