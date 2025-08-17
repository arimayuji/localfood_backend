package com.yuji.localfood.contracts;

import java.util.UUID;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.yuji.localfood.builders.FavoritePlaceTestDataBuilder;
import com.yuji.localfood.domain.repositories.FavoritePlaceRepository;

public abstract class FavoritePlaceContractTest {

  protected abstract FavoritePlaceRepository repo();

  @Test
  void createAndFindByUserIdAndPlaceId() {
    var userId = UUID.randomUUID();
    var placeId = UUID.randomUUID();

    var favoritePlace = FavoritePlaceTestDataBuilder.aFavoritePlace().withUserId(userId).withPlaceId(placeId).build();

    repo().create(favoritePlace);
    assertEquals(favoritePlace, repo().findByUserIdAndPlaceId(userId, placeId).get());
  }

  @Test
  void findAllByUserId() {
    var userId = UUID.randomUUID();

    var favoritePlace1 = FavoritePlaceTestDataBuilder.aFavoritePlace().withUserId(userId).build();
    var favoritePlace2 = FavoritePlaceTestDataBuilder.aFavoritePlace().withUserId(userId).build();

    repo().create(favoritePlace1);
    repo().create(favoritePlace2);

    assertEquals(2, repo().findByUserId(userId).get().size());
  }

  @Test
  void findAllByPlaceId() {
    var placeId = UUID.randomUUID();

    var favoritePlace1 = FavoritePlaceTestDataBuilder.aFavoritePlace().withPlaceId(placeId).build();
    var favoritePlace2 = FavoritePlaceTestDataBuilder.aFavoritePlace().withPlaceId(placeId).build();

    repo().create(favoritePlace1);
    repo().create(favoritePlace2);

    assertEquals(2, repo().findByPlaceId(placeId).get().size());
  }

  @Test
  void findAllByPlaceIdAndUserId() {
    var userId = UUID.randomUUID();
    var placeId = UUID.randomUUID();

    var favoritePlace1 = FavoritePlaceTestDataBuilder.aFavoritePlace().withUserId(userId).withPlaceId(placeId).build();
    var favoritePlace2 = FavoritePlaceTestDataBuilder.aFavoritePlace().withUserId(userId).withPlaceId(placeId).build();

    repo().create(favoritePlace1);
    repo().create(favoritePlace2);

    assertEquals(2, repo().findByUserIdAndPlaceId(userId, placeId).get().size());
  }

  @Test
  void delete() {
    var userId = UUID.randomUUID();
    var placeId = UUID.randomUUID();

    var favoritePlace = FavoritePlaceTestDataBuilder.aFavoritePlace().withUserId(userId).withPlaceId(placeId).build();

    repo().create(favoritePlace);
    repo().delete(userId, placeId);

    var found = repo().findByUserIdAndPlaceId(userId, placeId);
    assertTrue(found.isEmpty());
  }
}
