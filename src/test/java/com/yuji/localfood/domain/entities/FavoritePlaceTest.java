package com.yuji.localfood.domain.entities;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FavoritePlaceTest {
  private final UUID userId = UUID.randomUUID();
  private final UUID placeId = UUID.randomUUID();
  private final Instant now = Instant.parse("2023-01-01T00:00:00Z");

  @Test
  void shouldCreateValidFavoritePlace() {
    var favoritePlace = new FavoritePlace(userId, placeId, now);
    assertEquals(userId, favoritePlace.getUserId());
    assertEquals(placeId, favoritePlace.getPlaceId());
    assertEquals(now, favoritePlace.getCreatedAt());
  }

  @Test
  void shouldRejectNullFields() {
    Exception userIdNullException = assertThrows(IllegalArgumentException.class,
        () -> new FavoritePlace(null, placeId, now));
    Exception placeIdNullException = assertThrows(IllegalArgumentException.class,
        () -> new FavoritePlace(userId, null, now));
    Exception createdAtNullException = assertThrows(IllegalArgumentException.class,
        () -> new FavoritePlace(userId, placeId, null));

    assertEquals("userId", userIdNullException.getMessage());
    assertEquals("placeId", placeIdNullException.getMessage());
    assertEquals("createdAt", createdAtNullException.getMessage());
  }
}
