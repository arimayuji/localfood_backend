package com.yuji.localfood.fakes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.yuji.localfood.domain.entities.FavoritePlace;
import com.yuji.localfood.domain.enums.RankingOrder;
import com.yuji.localfood.domain.repositories.FavoritePlaceRepository;

public class InMemoryFavoritePlaceRepository implements FavoritePlaceRepository {
  private final Map<UUID, UUID> placeIds = new HashMap<>();
  private final Map<UUID, UUID> userIds = new HashMap<>();
  private final Map<UUID, FavoritePlace> favoritePlaces = new HashMap<>();
  private final Map<UUID, UUID> userIdsAndPlaceIds = new HashMap<>();

  @Override
  public FavoritePlace create(FavoritePlace favoritePlace) {
    final UUID userId = favoritePlace.getUserId();
    final UUID placeId = favoritePlace.getPlaceId();

    if (!placeIds.containsKey(placeId)) {
      throw new IllegalArgumentException("Place not found" + placeId);
    }

    if (!userIds.containsKey(userId)) {
      throw new IllegalArgumentException("User not found" + userId);
    }

    if (userIdsAndPlaceIds.containsKey(userId) && userIdsAndPlaceIds.get(userId).equals(placeId)) {
      throw new IllegalArgumentException("Favorite place already marked");
    }

    placeIds.put(placeId, placeId);
    userIds.put(userId, userId);
    favoritePlaces.put(placeId, favoritePlace);
    userIdsAndPlaceIds.put(userId, placeId);

    return favoritePlace;
  }

  @Override
  public FavoritePlace update(FavoritePlace favoritePlace) {
    final UUID userId = favoritePlace.getUserId();
    final UUID placeId = favoritePlace.getPlaceId();

    if (!placeIds.containsKey(placeId)) {
      throw new IllegalArgumentException("Place not found" + placeId);
    }

    if (!userIds.containsKey(userId)) {
      throw new IllegalArgumentException("User not found" + userId);
    }

    placeIds.put(placeId, placeId);
    userIds.put(userId, userId);
    favoritePlaces.put(placeId, favoritePlace);
    userIdsAndPlaceIds.put(userId, placeId);

    return favoritePlace;
  }

  @Override
  public FavoritePlace orderByDateAndUserId(UUID userId, RankingOrder order) {
    throw new UnsupportedOperationException("Unimplemented method 'orderByDateAndUserId'");
  }

  @Override
  public Optional<List<FavoritePlace>> findByPlaceId(UUID placeId) {
    if (!placeIds.containsKey(placeId)) {
      throw new IllegalArgumentException("Place not found" + placeId);
    }

    return Optional.ofNullable(favoritePlaces.values().stream().filter(f -> f.getPlaceId().equals(placeId)).toList());
  }

  @Override
  public Optional<List<FavoritePlace>> findByUserIdAndPlaceId(UUID userId, UUID placeId) {
    if (!placeIds.containsKey(placeId)) {
      throw new IllegalArgumentException("Place not found" + placeId);
    }

    if (!userIds.containsKey(userId)) {
      throw new IllegalArgumentException("User not found" + userId);
    }

    return Optional.ofNullable(favoritePlaces.values().stream()
        .filter(f -> f.getPlaceId().equals(placeId) && f.getUserId().equals(userId)).toList());
  }

  @Override
  public void delete(UUID userId, UUID placeId) {
    if (!placeIds.containsKey(placeId)) {
      throw new IllegalArgumentException("Place not found" + placeId);
    }

    if (!userIds.containsKey(userId)) {
      throw new IllegalArgumentException("User not found" + userId);
    }

    favoritePlaces.remove(placeId);
    userIdsAndPlaceIds.remove(userId);
  }

  @Override
  public Optional<List<FavoritePlace>> findByUserId(UUID userId) {
    if (!userIds.containsKey(userId)) {
      throw new IllegalArgumentException("User not found" + userId);
    }

    return Optional.ofNullable(favoritePlaces.values().stream().filter(f -> f.getUserId().equals(userId)).toList());
  }

}
