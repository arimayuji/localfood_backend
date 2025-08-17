package com.yuji.localfood.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.yuji.localfood.domain.entities.Review;
import com.yuji.localfood.domain.enums.RankingOrder;

public interface ReviewRepository {
  Review create(Review review);

  Review update(Review newReview);

  Optional<Review> findById(UUID id);

  Optional<List<Review>> findByUserId(UUID userId);

  Optional<List<Review>> findByPlaceId(UUID placeId);

  Optional<List<Review>> findAll();

  Optional<List<Review>> orderByRating(RankingOrder order);

  Optional<List<Review>> orderByDate(RankingOrder order);

  void delete(UUID id);
}
