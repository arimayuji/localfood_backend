package com.yuji.localfood.contracts;

import java.time.Instant;
import java.util.UUID;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.yuji.localfood.domain.entities.Review;
import com.yuji.localfood.domain.enums.ReviewAudience;
import com.yuji.localfood.domain.repositories.ReviewRepository;

public abstract class ReviewRepositoryContractTest {

  protected abstract ReviewRepository repo();

  @Test
  void createThenFindById() {
    var review = new Review(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 5, "comment", "tip",
        ReviewAudience.GENERAL, Instant.now(), Instant.now());

    repo().create(review);
    var found = repo().findById(review.getId());

    assertTrue(found.isPresent());
    assertEquals(review, found);
  }

  @Test
  void createThenFindByPlaceId() {
    var review = new Review(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 5, "comment", "tip",
        ReviewAudience.GENERAL, Instant.now(), Instant.now());

    repo().create(review);
    var found = repo().findByPlaceId(review.getPlaceId());

    assertTrue(found.isPresent());

    var place = found.get();
    assertEquals(review, place.get(0));
    assertEquals(1, place.size());
  }

  @Test
  void createThenFindByUserId() {
    var review = new Review(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 5, "comment", "tip",
        ReviewAudience.GENERAL, Instant.now(), Instant.now());

    repo().create(review);
    var found = repo().findByUserId(review.getUserId());

    assertTrue(found.isPresent());

    var reviews = found.get();
    assertEquals(review, reviews.get(0));
    assertEquals(1, reviews.size());
  }

  @Test
  void createAndFindAll() {
    var review = new Review(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 5, "comment", "tip",
        ReviewAudience.GENERAL, Instant.now(), Instant.now());

    repo().create(review);
    var found = repo().findAll();

    assertTrue(found.isPresent());

    var reviews = found.get();
    assertEquals(review, reviews.get(0));
    assertEquals(1, found.get().size());
  }
}
