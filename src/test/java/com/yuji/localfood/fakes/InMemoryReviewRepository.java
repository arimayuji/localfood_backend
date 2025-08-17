package com.yuji.localfood.fakes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.yuji.localfood.domain.entities.Review;
import com.yuji.localfood.domain.enums.RankingOrder;
import com.yuji.localfood.domain.repositories.ReviewRepository;

public class InMemoryReviewRepository implements ReviewRepository {
  private final Map<UUID, Review> reviews = new HashMap<>();
  private final Map<UUID, UUID> placeIds = new HashMap<>();
  private final Map<UUID, UUID> userIds = new HashMap<>();

  @Override
  public Review create(Review review) {
    final UUID reviewId = review.getId();

    if (reviews.containsKey(reviewId)) {
      throw new IllegalArgumentException("Review already exists" + reviewId);
    }

    if (!placeIds.containsKey(review.getPlaceId())) {
      throw new IllegalArgumentException("Place not found" + review.getPlaceId());
    }

    if (!userIds.containsKey(review.getUserId())) {
      throw new IllegalArgumentException("User not found" + review.getUserId());
    }

    reviews.put(reviewId, review);
    return review;
  }

  @Override
  public Review update(Review newReview) {
    final UUID reviewId = newReview.getId();
    final Review oldReview = reviews.get(reviewId);

    if (!reviews.containsKey(reviewId)) {
      throw new IllegalArgumentException("Review not found" + reviewId);
    }

    if (!oldReview.getUserId().equals(newReview.getUserId())) {
      if (!userIds.containsKey(newReview.getUserId())) {
        throw new IllegalArgumentException("User not found" + newReview.getUserId());
      }
      userIds.remove(oldReview.getUserId());
      userIds.put(newReview.getUserId(), reviewId);
    }

    if (!oldReview.getPlaceId().equals(newReview.getPlaceId())) {
      if (!placeIds.containsKey(newReview.getPlaceId())) {
        throw new IllegalArgumentException("Place not found" + newReview.getPlaceId());
      }
      placeIds.remove(oldReview.getPlaceId());
      placeIds.put(newReview.getPlaceId(), reviewId);
    }

    reviews.put(reviewId, newReview);
    return newReview;
  }

  @Override
  public Optional<Review> findById(UUID id) {
    return Optional.ofNullable(reviews.get(id));
  }

  @Override
  public Optional<List<Review>> findByUserId(UUID userId) {
    List<Review> reviewsByUserId = this.reviews.values().stream().filter(r -> r.getUserId().equals(userId)).toList();

    return Optional.ofNullable(reviewsByUserId);
  }

  @Override
  public Optional<List<Review>> findByPlaceId(UUID placeId) {
    List<Review> reviewsByPlaceId = this.reviews.values().stream().filter(r -> r.getPlaceId().equals(placeId)).toList();

    return Optional.ofNullable(reviewsByPlaceId);
  }

  @Override
  public Optional<List<Review>> findAll() {
    return Optional.ofNullable(reviews.values().stream().toList());
  }

  @Override
  public Optional<List<Review>> orderByRating(RankingOrder order) {
    throw new UnsupportedOperationException("Unimplemented method 'orderByRating'");
  }

  @Override
  public Optional<List<Review>> orderByDate(RankingOrder order) {
    throw new UnsupportedOperationException("Unimplemented method 'orderByDate'");
  }

  @Override
  public void delete(UUID id) {
    if (!reviews.containsKey(id)) {
      throw new IllegalArgumentException("Review not found" + id);
    }

    final Review removedReview = reviews.get(id);

    userIds.remove(removedReview.getUserId());
    placeIds.remove(removedReview.getPlaceId());
    reviews.remove(id);
  }

}
