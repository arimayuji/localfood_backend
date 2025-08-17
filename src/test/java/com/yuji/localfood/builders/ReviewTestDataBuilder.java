package com.yuji.localfood.builders;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

import com.yuji.localfood.domain.entities.Review;
import com.yuji.localfood.domain.enums.ReviewAudience;

public class ReviewTestDataBuilder {

  private static final Random rnd = new Random(42);

  private UUID id = UUID.randomUUID();
  private UUID placeId = UUID.randomUUID();
  private UUID userId = UUID.randomUUID();
  private final String comment = "Comment-" + rnd.nextInt(10_000);
  private final String tipForForeigners = "Tip-" + rnd.nextInt(10_000);
  private final ReviewAudience audience = ReviewAudience.FOREIGNERS;
  private final Instant createdAt = Instant.now();
  private final Instant updatedAt = Instant.now();

  public static ReviewTestDataBuilder aReview() {
    return new ReviewTestDataBuilder();
  }

  public ReviewTestDataBuilder withId(UUID id) {
    this.id = id;
    return this;
  }

  public ReviewTestDataBuilder withPlaceId(UUID placeId) {
    this.placeId = placeId;
    return this;
  }

  public ReviewTestDataBuilder withUserId(UUID userId) {
    this.userId = userId;
    return this;
  }

  public Review build() {
    return new Review(id, userId, placeId, 5, comment, tipForForeigners, audience, createdAt, updatedAt);
  }

}
