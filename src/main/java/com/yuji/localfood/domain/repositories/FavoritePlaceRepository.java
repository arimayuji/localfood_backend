package com.yuji.localfood.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.yuji.localfood.domain.entities.FavoritePlace;
import com.yuji.localfood.domain.enums.RankingOrder;

public interface FavoritePlaceRepository {
  FavoritePlace create(FavoritePlace favoritePlace);

  FavoritePlace update(FavoritePlace favoritePlace);

  Optional<List<FavoritePlace>> findByUserId(UUID userId);

  Optional<List<FavoritePlace>> findByPlaceId(UUID placeId);

  Optional<List<FavoritePlace>> findByUserIdAndPlaceId(UUID userId, UUID placeId);

  FavoritePlace orderByDateAndUserId(UUID userId, RankingOrder order);

  void delete(UUID userId, UUID placeId);
}
